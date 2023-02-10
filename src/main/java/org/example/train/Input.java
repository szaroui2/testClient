package org.example.train;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Input {
    private List<Tap> taps=new ArrayList<>();

    public List<Tap> getTaps() {
        return taps;
    }

    public void setTaps(List<Tap> taps) {
        this.taps = taps;
    }



    public Map<Integer,List<Tap>> groupTapsByCustomerId(List<Tap> taps) {
        Map<Integer,List<Tap>> tapsByCustomer = taps.stream().collect(Collectors.groupingBy(Tap::getCustomerId));
        return tapsByCustomer;
    }


}
