package server.network.event;

import lib.cluster.SoCeServer;
import lib.logger.LoggerInstance;
import lib.network.message.INetworkMessage;
import lib.network.message.handler.factory.NetworkHandlerFactory;
import lib.server.hazelcast.HazelcastManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Justin on 03.12.2014.
 */
public class EventQueueThreadPool implements Runnable {

    protected List<EventQueueHandlerThread> eventQueueHandlerThreadList = new ArrayList<EventQueueHandlerThread>();
    protected int maxThreads = 5;
    protected boolean isInterrupted = false;
    protected SoCeServer server = null;
    protected int maxEventsperHandlerThread = 5;
    protected BlockingQueue<INetworkMessage> eventQueue = null;
    protected int minHandlerThreads = 3;
    protected NetworkHandlerFactory networkHandlerFactory = null;

    public EventQueueThreadPool (SoCeServer server, NetworkHandlerFactory networkHandlerFactory) {
        this.server = server;
        this.networkHandlerFactory = networkHandlerFactory;
    }

    @Override
    public void run() {
        //cleanup

        //create min threads
        for (int i = 0; i < this.minHandlerThreads; i++) {
            //start new Handler Thread
            this.startNewEventQueueHandlerThread();
        }

        //get queue
        this.eventQueue = HazelcastManager.getClient().getQueue("server-event-queue-" + this.server.getServerID());

        while (!this.isInterrupted) {
            //check if there enough threads for handling the requests
            if (this.eventQueue.size() > 0 && this.eventQueue.size() / this.maxEventsperHandlerThread > this.eventQueueHandlerThreadList.size()) {
                //check if the thread pool can create a new thread for handling the requests
                if (this.eventQueueHandlerThreadList.size() < this.maxThreads) {
                    //start new Handler Thread
                    this.startNewEventQueueHandlerThread();
                }
            }

            //check if there are more threads than maxThreads
            if (this.eventQueueHandlerThreadList.size() > this.maxThreads) {
                //force to close an thread
                this.eventQueueHandlerThreadList.get(this.eventQueueHandlerThreadList.size() - 1).interrupt();
            }

            try {
                //wait 100 ms
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startNewEventQueueHandlerThread () {
        EventQueueHandlerThread eventQueueHandlerThread = new EventQueueHandlerThread(this.eventQueue, this.networkHandlerFactory);
        eventQueueHandlerThread.setEventQueueHandlerThreadFinishedListener(this::onEventQueueHandlerThreadFinsihed);

        LoggerInstance.getLogger().debug("Create new EventQueueHandlerThread.");

        //put thread into the list
        this.eventQueueHandlerThreadList.add(eventQueueHandlerThread);

        //start new thread
        Thread thread = new Thread(eventQueueHandlerThread);
        thread.start();
    }

    public void interrupt () {
        this.isInterrupted = true;
    }

    public synchronized void onEventQueueHandlerThreadFinsihed (EventQueueHandlerThread eventQueueHandlerThread) {
        //remove thread from thread pool
        this.eventQueueHandlerThreadList.remove(eventQueueHandlerThread);
    }

    public synchronized int getThreads () {
        return this.eventQueueHandlerThreadList.size();
    }

    public synchronized void setMaxThreads (int maxThreads) {
        this.maxThreads = maxThreads;
    }
}
