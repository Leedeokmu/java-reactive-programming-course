package com.freeefly;

import com.rp.courseutil.Util;
import com.rp.sec08.helper.AmericanAirlines;
import com.rp.sec08.helper.Emirates;
import com.rp.sec08.helper.NameGenerator;
import com.rp.sec08.helper.Qatar;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class CombiningPublishersTest {
    public static void main(String[] args) {
//        /**
//         * startWith
//         */
//        NameGenerator generator = new NameGenerator ();
//        generator.generateNames()
//                .take(2)
//                .subscribe(Util.subscriber("sam"));
//        generator.generateNames()
//                .take(2)
//                .subscribe(Util.subscriber("mike"));
//        /**
//         * merge
//         */
//        Flux.merge(Qatar.getFlights(), Emirates.getFlights(), AmericanAirlines.getFlights())
//                .subscribe(Util.subscriber());
//        Util.sleepSeconds(10);

        /**
         * zip
         */
        Flux.zip(Qatar.getFlights(), Emirates.getFlights(), AmericanAirlines.getFlights())
                .subscribe(Util.subscriber());
        Util.sleepSeconds(10);





    }
}
