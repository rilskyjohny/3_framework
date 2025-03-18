package su.rj._3;

/*
 * States of a server.
 * ACCESSED state is mostly like VALID.
 *
 * State DESTRUCTIBLE means many checks and slower work.
 * For example, a man called getSrv method.
 *
 * States VALID and INVALID are self-describing.
 */
public enum ServerStates {
    VALID,ACCESSED,DESTRUCTIBLE,INVALID;
}
