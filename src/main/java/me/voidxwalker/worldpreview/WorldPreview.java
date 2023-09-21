package me.voidxwalker.worldpreview;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.resource.Resource;
import net.minecraft.util.Language;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WorldPreview implements ClientModInitializer {
    public static final Object lock = new Object();
    public static World world;
    public static ClientPlayerEntity player;
    public static ClientWorld clientWorld;
    public static boolean inPreview;
    public static boolean renderingPreview;
    public static BlockPos spawnPos;
    public static int kill = 0;
    public static int playerSpawn;
    public static Camera camera;
    public static WorldRenderer worldRenderer;
    public static boolean existingWorld;
    public static boolean showMenu;
    public static boolean calculatedSpawn;
    public static KeyBinding resetKey;
    public static KeyBinding freezeKey;
    public static boolean freezePreview;
    public static boolean hasStateOutput;
    public static Logger LOGGER = LogManager.getLogger();

    public static void log(Level level, String message) {
        LOGGER.log(level, message);
    }

    public static void loadTranslations(List<Resource> resources, Map<String, String> translationMap) {
        resources.forEach(resource ->
                Optional.ofNullable(WorldPreview.class.getResourceAsStream("assets/worldpreview/" + resource.getId().getPath()))
                        .ifPresent(langStream -> Language.load(langStream, translationMap::put))
        );
    }

    @Override
    public void onInitializeClient() {
        hasStateOutput = FabricLoader.getInstance().isModLoaded("state-output");

        resetKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Leave Preview",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                "key.categories.world_preview"
        ));

        freezeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Freeze Preview",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_J,
                "key.categories.world_preview"
        ));
    }
}
