/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.model.cells;

import java.awt.Point;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import snake.model.Area;
import snake.model.creatures.Creature;

/**
 *
 * @author rasmadeus
 */
class OneCreatureContainer extends EmptyRenderCell {

    public OneCreatureContainer(Area area) {
        super(area);
    }

    @Override
    public boolean take(Creature catcher) {
        if (!takeGuard.tryLock()) {
            return false;            
        }
        try {
            if (owner != null) {
                return false;
            }
            owner = catcher;
            return true;
        }
        finally {
            takeGuard.unlock();
        }
    }

    @Override
    public boolean isTaked() {
        takeGuard.lock();
        try {
            return owner != null;
        }
        finally {
            takeGuard.unlock();
        }
    }

    @Override
    public boolean move(Point dest, Creature catcher) {
        if (!catchGuard.tryLock()) {
            return false;
        }
        try {
            if (area.take(dest, catcher)) {
                owner = null;
                return true;
            }
            return false;
        }
        finally {
            catchGuard.unlock();
        }
    }

    @Override
    public boolean catchCreature(Creature predator) {
        catchGuard.lock();
        try {
            if (owner != null) {
                predator.eat(owner);
                return true;
            }
            return false;
        }
        finally {
            owner = predator;
            catchGuard.unlock();
        }
    }

    private Creature owner = null;    
    
    private final Lock takeGuard = new ReentrantLock();

    private final Lock catchGuard = new ReentrantLock();    

    @Override
    public void release() {
        takeGuard.lock();
        try {
            owner = null;
        } 
        finally {
            takeGuard.unlock();
        }
    }
}
