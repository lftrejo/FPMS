package com.se2.team3.fpms;

import java.io.Serializable;

/**
 * Created by Scott on 4/30/2015.
 */
public class Resource
    implements Serializable {
        protected String name;

        public Resource() { this.name = "Avenger Cameo"; }

        public Resource(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public boolean equalTo(String name) {
            return this.name.equalsIgnoreCase(name.toLowerCase());
        }
    }
