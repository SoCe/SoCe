package server.network.event;

import lib.network.message.INetworkMessage;
import lib.network.message.handler.INetworkHandler;
import lib.network.message.handler.INetworkHandlerManager;
import lib.network.message.handler.factory.NetworkHandlerFactory;
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
    protected NetworkHandlerFactory networkHandlerFactory = null;

    public EventQueueHandlerThread (BlockingQueue<INetworkMessage> eventQueue, NetworkHandlerFactory networkHandlerFactory) {
        this.eventQueue = eventQueue;
        this.networkHandlerFactory = networkHandlerFactory;
    }

    @Override
    public void run() {
        while (!this.isInterrupted) {
            //work in queue

            if (this.eventQueue.size() > 0) {
                //get NetworkMessage
                INetworkMessage message = this.eventQueue.poll();

                //get handler
                INetworkHandler networkHandler = this.networkHandlerFactory.buildNetworkHandler(message);
                networkHandler.receive(message.getChannelHandlerContext(), message);
            } else {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
