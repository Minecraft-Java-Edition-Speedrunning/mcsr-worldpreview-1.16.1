package me.voidxwalker.worldpreview.mixin.server.shutdown;

import me.voidxwalker.worldpreview.IFastCloseable;
import net.minecraft.util.thread.TaskExecutor;
import net.minecraft.util.thread.TaskQueue;
import net.minecraft.world.storage.RegionBasedStorage;
import net.minecraft.world.storage.StorageIoWorker;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StorageIoWorker.class)
public class StorageIOWorkerMixin implements IFastCloseable {
    @Shadow @Final private TaskExecutor<TaskQueue.PrioritizedTask> field_24468;

    @Shadow @Final private RegionBasedStorage storage;

    @Shadow @Final private static Logger LOGGER;

    @Override
    public void fastClose() {
        this.field_24468.close();
        try {
            this.storage.close();
        } catch (Exception var3) {
            this.LOGGER.error("Failed to close storage", (Throwable)var3);
        }
    }
}
