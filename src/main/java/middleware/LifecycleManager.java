package middleware;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class LifecycleManager {

    //This integer emulates a stack of invocations
    public static AtomicInteger invocationStack = new AtomicInteger(0);

    /**
     * prepares the remote object for a new invocation by activating the remote object (is he is inactive) and increments
     * the invocation stack making reference at the new invocation tha has arrived.
     */
    public static void prepareRemoteObjectForInvocation () {
        invocationStack.incrementAndGet();
        if(RemoteObject.isActive() ) { return ; }
        RemoteObject.setActive();
        return;
    }

    /**
     * prepares the remote object for a deactivation if there is no more invocations to be processed
     */
    public static void prepareRemoteObjectForDeactivation () {
        invocationStack.decrementAndGet();
        if (invocationStack.get() > 0) { return; }
        RemoteObject.setInactive();
    }
}
