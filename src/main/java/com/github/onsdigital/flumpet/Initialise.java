package com.github.onsdigital.flumpet;

import com.github.davidcarboni.restolino.framework.Startup;
import com.github.onsdigital.flumpet.helpers.Recv;

/**
 * Example initialisation task.
 * Take a look at the {@code com.github.davidcarboni.restolino.framework} package for more useful interfaces/classes.
 */
public class Initialise implements Startup {
    @Override
    public void init() {
        //System.out.println(" >>>>>>>>>>> init >>>>>>>>>>>>> creating queue consumer...");

        //Recv.startWrapper();
    }
}
