package io.github.acezhuang.java8;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

public class GuavaDemo {

    static EventBus bus = new EventBus();

    static {
        bus.register(new GuavaDemo());
    }

    public static void main(String[] args) {
        testMap();
        testBiMap();
        testEventBus();
    }

    public static void testEventBus(){
        bus.post(new AEvent(11));
    }

    private static void testMap(){
        ArrayListMultimap<Object, Object> mMap = ArrayListMultimap.create();
        ArrayList<Integer> ints = Lists.newArrayList(1, 2, 2, 3, 4);
        ints.forEach(item->{
            mMap.put(item, item +1);
        });
        System.out.println(mMap);
    }

    private static void testBiMap(){
        BiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("hh", 1);
        biMap.put("hoho", 2);
        System.out.println(biMap.inverse().get(2));
    }

    @Data
    @AllArgsConstructor
    public static class AEvent{
        private int id;
    }

    @Subscribe
    public void handle(AEvent aEvent){
        System.out.println(Thread.currentThread().getName() + " " + aEvent.getId());
    }
}
