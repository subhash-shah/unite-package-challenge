package com.unite.challenge.entity;

import java.util.Collections;

/**
 * Null Object pattern implementation of Package
 */
public class EmptyPackage extends Package {
    public EmptyPackage() {
        super(-1.0, Collections.emptyList());
    }
}
