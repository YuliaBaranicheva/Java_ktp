package com.company;

/**
 * Этот класс представляет собой один шаг в пути, сгенерированном алгоритмом A* pathfinding.
 * Путевые точки состоят из местоположения, предыдущей путевой точки в
 * пути и некоторых значений затрат, используемых для определения наилучшего пути.
 **/

public class Waypoint {
    /** Местоположение этой путевой точки **/
    Location loc;

    /**
     * Предыдущая путевая точка на этом пути, или null, если это
     * корень поиска A*.
     **/
    Waypoint prevWaypoint;

    /**
     * В этом поле хранится общая предыдущая стоимость получения от начального
     * местоположения до этой путевой точки по цепочке путевых точек. Это
     * фактическая стоимость следования по пути; она не включает никаких оценок.
     **/
    private float prevCost;

    /**
     * В этом поле хранится оценка оставшейся стоимости поездки из
     * этой путевой точки к конечному пункту назначения.
     **/
    private float remainingCost;


    /**
     * Создание новой путевой точки для указанного местоположения. Предыдущая путевая точка
     * может быть дополнительно указано, или ссылка может быть null, чтобы
     * указать, что путевая точка является началом пути.
     **/
    public Waypoint(Location loc, Waypoint prevWaypoint)
    {
        this.loc = loc;
        this.prevWaypoint = prevWaypoint;
    }

    /** Возвращает местоположение путевой точки. **/
    public Location getLocation()
    {
        return loc;
    }

    /**
     * Возвращает предыдущую путевую точку в пути или null, если это начало пути.
     **/
    public Waypoint getPrevious()
    {
        return prevWaypoint;
    }

    /**
     * Этот мутатор позволяет устанавливать как предыдущую стоимость, так и оставшуюся стоимость
     * в одном вызове метода. Обычно эти значения в любом случае
     * устанавливаются в одно и то же время
     **/
    public void setCosts(float prevCost, float remainingCost)
    {
        this.prevCost = prevCost;
        this.remainingCost = remainingCost;
    }

    /**
     * Возвращает фактическую стоимость перехода к этой точке из начального
     * * местоположения, через ряд путевых точек в этой цепочке.
     **/
    public float getPreviousCost()
    {
        return prevCost;
    }

    /**
     * Возвращает оценку оставшейся стоимости проезда от этой точки до конечного пункта назначения
     **/
    public float getRemainingCost()
    {
        return remainingCost;
    }

    /**
     * Возвращает общую смету затрат для этой путевой точки. Это включает в
     * себя фактическую стоимость проезда до этой точки из исходного местоположения, плюс
     * оценка оставшейся стоимости проезда от этого пункта до
     * конечного пункта назначения.
     **/
    public float getTotalCost()
    {
        return prevCost + remainingCost;
    }
}
