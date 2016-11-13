package com.aplication.material.sortumanen.managers;


public class StatusManager {
    private static StatusManager instance;

    private enum StatusEnum { IDLE, FILTER }
    private StatusEnum currentStatus = StatusEnum.IDLE;

    /**
     *
     * @return
     */
    public static StatusManager getInstance() {
        return instance == null ? instance = new StatusManager() : instance;
    }

    /**
     *
     * @return
     */
    public StatusEnum getCurrentStatus() {
        return currentStatus;
    }

    /**
     *
     * @return
     */
    public StatusEnum setFilterMode() {
        return currentStatus = StatusEnum.FILTER;
    }

    /**
     *
     * @return
     */
    public StatusEnum setIdleMode() {
        return currentStatus = StatusEnum.IDLE;
    }

    /**
     *
     * @return
     */
    public boolean isFilterMode() {
        return currentStatus == StatusEnum.FILTER;
    }

    /**
     *
     * @return
     */
    public boolean isIdleMode() {
        return currentStatus == StatusEnum.IDLE;
    }

}
