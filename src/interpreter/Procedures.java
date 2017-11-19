package interpreter;

import storageManager.Disk;
import storageManager.MainMemory;
import storageManager.SchemaManager;

public class Procedures {
    MainMemory mem=new MainMemory();
    Disk disk=new Disk();
    SchemaManager schema_manager;

    public Procedures(MainMemory mem, Disk disk, SchemaManager schema_manager) {
        this.mem = (mem != null)? mem: new MainMemory();
        this.disk = (disk != null)? disk: new Disk();
        this.schema_manager = (schema_manager != null)? schema_manager: new SchemaManager(this.mem, this.disk);
    }

    public MainMemory getMem() {
        return mem;
    }

    public Disk getDisk() {
        return disk;
    }

    public SchemaManager getSchema_manager() {
        return schema_manager;
    }
}
