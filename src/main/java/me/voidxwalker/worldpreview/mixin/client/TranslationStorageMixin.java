package me.voidxwalker.worldpreview.mixin.client;

import me.voidxwalker.worldpreview.WorldPreview;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.resource.Resource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(TranslationStorage.class)
public class TranslationStorageMixin {
    @Inject(method = "load(Ljava/util/List;Ljava/util/Map;)V", at = @At(value = "RETURN"))
    private static void onLoad(List<Resource> resources, Map<String, String> translationMap, CallbackInfo ci) {
        WorldPreview.loadTranslations(resources, translationMap);
    }
}
