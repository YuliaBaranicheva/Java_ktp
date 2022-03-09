package com.company;

import java.util.HashMap;
import java.util.Map;

import java.util.Set;
import java.util.Iterator;

/**
 Этот класс хранит базовое состояние, необходимое алгоритму A* для вычисления пути по карте.
 Это состояние включает в себя набор "открытых путевых точек" и другой набор "закрытых путевых точек". Кроме того, этот класс предоставляет
 основные операции, необходимые алгоритму поиска пути A* для выполнения его
 обработки.
 **/

public class AStarState {
    /** Это ссылка на карту, по которой перемещается алгоритм A*. **/
    private Map2D map;

    private Map<Location, Waypoint> open_waypoints
            = new HashMap<Location, Waypoint>();
    private Map<Location, Waypoint> closed_waypoints
            = new HashMap<Location, Waypoint>();

    /**
     * Инициализирует новый объект состояния для использования алгоритма поиска пути A*.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("карта не может быть нулевой");

        this.map = map;
    }

    /** Возвращает карту, по которой перемещается навигатор A*. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * Этот метод сканирует все открытые путевые точки и возвращает путевую точку
     * с минимальной общей стоимостью.
     * Если открытых путевых точек нет, этот метод возвращает null
     **/
    public Waypoint getMinOpenWaypoint()
    {
        // Если нет открытых вершин, возвращаем null.
        if (numOpenWaypoints() == 0)
            return null;
        Set open_waypoint_keys = open_waypoints.keySet();
        Iterator i = open_waypoint_keys.iterator();
        Waypoint best = null;
        float best_cost = Float.MAX_VALUE;
        // Проверяет все вершины.
        while (i.hasNext()) {
            // Сохраняет текущую локацию.
            Location location = (Location) i.next();
            // Сохраняет текущую вершину.
            Waypoint waypoint = open_waypoints.get(location);
            // Сохраняет текущую "стоимость" вершины.
            float waypoint_total_cost = waypoint.getTotalCost();

            // Если общая "стоимость" текущей вершины лучше (меньше)
            // тогда сохраненная "стоимость" сохраненной лучшей вершины заменяет
            // сохраненную вершину текущей вершины и сохраненную общую "стоимость"
            // текущей общей "стоимостью".
            if (waypoint_total_cost < best_cost) {
                best = open_waypoints.get(location);
                best_cost = waypoint_total_cost;
            }
        }
        // Возвращает вершину с минимальной общей "стоимостью".
        return best;
    }

    /**
     * Этот метод добавляет путевую точку (или потенциально обновляет уже имеющуюся путевую точку)
     * в коллекцию "открытые путевые точки". Если в местоположении новых путевых точек
     * еще нет открытой путевой точки, то новая путевая точка просто добавляется в коллекцию.
     * Однако, если в местоположении новых путевых точек уже есть путевая точка,
     * новая путевая точка заменяет старую only if значение "предыдущая стоимость" новых путевых точек
     * меньше значения "предыдущая стоимость" текущих путевых точек.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        // Находит локацию новой вершины.
        Location location = newWP.getLocation();

        // Проверяет есть ли уже открытая вершина на месте новой вершины.
        if (open_waypoints.containsKey(location)) {
            // Если на этом месте уже есть открытая вершина,
            // проверяет меньше ли "предыдущая стоимость" новой вершины
            // "предыдущей стоимости" текущей.
            Waypoint current_waypoint = open_waypoints.get(location);
            if (newWP.getPreviousCost() < current_waypoint.getPreviousCost()) {
                // Если "предыдущая стоимость" новой вершины меньше
                // "предыдущей стоимости" текущей вершины, новая вершина
                // заменяет старую и возвращает true.
                open_waypoints.put(location, newWP);
                return true;
            }
            // Если "предыдущая стоимость" новой вершины не меньше "предыдущей стоимости"
            // текущей вершины, return false.
            return false;
        }
        // Если в наборе «открытых вершин» в настоящее время нет вершины для данного места,
        // то необходимо просто добавить новую вершину и return true.
        open_waypoints.put(location, newWP);
        return true;
    }


    /** Возвращает текущее количество открытых путевых точек. **/
    public int numOpenWaypoints()
    {
        return open_waypoints.size();
    }


    /**
     * Этот метод перемещает путевую точку в указанном местоположении из
     * открытого списка в закрытый список.
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint waypoint = open_waypoints.remove(loc);
        closed_waypoints.put(loc, waypoint);
    }

    /**
     * Возвращает значение true, если коллекция закрытых путевых точек содержит путевую точку
     * для указанного местоположения.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closed_waypoints.containsKey(loc);
    }



}
