package org.blackteasea.revival;

import org.bukkit.Location;

public class Storage {
    private Location loc;
    private boolean revived;

    public Storage(Location loc, boolean revived){
        this.loc = loc;
        this.revived = revived;
    }

    public Location getLoc() {
        return loc;
    }
    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public boolean isRevived() {
        return revived;
    }
    public void setRevived(boolean revived) {
        this.revived = revived;
    }
}
