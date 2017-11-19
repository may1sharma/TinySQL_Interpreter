package interpreter;

import storageManager.Disk;
import storageManager.MainMemory;
import storageManager.SchemaManager;

public class DeleteProc extends Procedures {
    public DeleteProc(MainMemory mem, Disk disk, SchemaManager schema_manager) {
        super(mem, disk, schema_manager);
    }
}
