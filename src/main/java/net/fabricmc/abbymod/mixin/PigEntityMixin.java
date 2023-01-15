package net.fabricmc.abbymod.mixin;

import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(PigEntity.class)
public class PigEntityMixin {

    @Shadow @Final private static Ingredient BREEDING_INGREDIENT;

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/recipe/Ingredient;ofItems([Lnet/minecraft/item/ItemConvertible;)Lnet/minecraft/recipe/Ingredient;"
            )
    )
    private static Ingredient ofItems(ItemConvertible[] items) {
        var list = new ArrayList<>(Arrays.stream(items).toList());
        list.add(Items.APPLE);
        return Ingredient.ofItems(list.toArray(new ItemConvertible[0]));
    }

}
