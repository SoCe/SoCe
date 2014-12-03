package server.network.event;

import lib.network.message.INetworkMessage;
import server.network.event.listener.EventQueueHandlerThreadFinishedListener;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Justin on 03.12.2014.
 */
public class EventQueueHandlerThread implements Runnable {

    protected boolean isInterrupted = false;
    protected boolean hasFinished = false;
    protected EventQueueHandlerThreadFinishedListener eventQueueHandlerThreadFinishedListener = null;
    protected BlockingQueue<INetworkMessage> eventQueue = null;

    public EventQueueHandlerThread (BlockingQueue<INetworkMessage> eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void run() {
        while (!this.isInterrupted) {
            //work in queue

            //get NetworkMessage
            INetworkMessage message = this.eventQueue.poll();
        }

        this.hasFinished = true;

        if (this.eventQueueHandlerThreadFinishedListener != null) {
            this.eventQueueHandlerThreadFinishedListener.onFinishHandlerThread(this);
        }
    }

    public void setEventQueueHandlerThreadFinishedListener (EventQueueHandlerThreadFinishedListener eventQueueHandlerThreadFinishedListener) {
        this.eventQueueHandlerThreadFinishedListener = eventQueueHandlerThreadFinishedListener;
    }

    public synchronized void interrupt () {
        this.isInterrupted = true;
    }

    public synchronized boolean hasFinished () {
        return this.hasFinished;
    }

}
