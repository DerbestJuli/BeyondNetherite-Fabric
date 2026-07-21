package de.julirix.beyondnetherite.crafting;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface ModRecipes<T extends Recipe<?>> {
    RecipeType<SmelteringRecipe> SMELTERING = register("smeltering");

    static <T extends Recipe<?>> RecipeType<T> register(String name) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.withDefaultNamespace(name), new RecipeType<T>() {
            @Override
            public String toString() {
                return name;
            }
        });
    }

    static <T extends Recipe<?>> RecipeType<T> simple(final Identifier name) {
        final String toString = name.toString();
        return new RecipeType<T>() {
            @Override
            public String toString() {
                return toString;
            }
        };
    }
}
