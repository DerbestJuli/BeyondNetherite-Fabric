package de.julirix.beyondnetherite.crafting;

import com.mojang.serialization.MapCodec;
import de.julirix.beyondnetherite.block.ModBlocks;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import org.jspecify.annotations.NonNull;

public class SmelteringRecipe extends AbstractCookingRecipe {
    public static final MapCodec<SmelteringRecipe> MAP_CODEC = cookingMapCodec(SmelteringRecipe::new, 200);
    public static final StreamCodec<RegistryFriendlyByteBuf, SmelteringRecipe> STREAM_CODEC = cookingStreamCodec(SmelteringRecipe::new);
    public static final RecipeSerializer<SmelteringRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    public SmelteringRecipe(
            CommonInfo commonInfo,
            CookingBookInfo bookInfo,
            Ingredient ingredient,
            ItemStackTemplate result,
            float experience,
            int cookingTime)
    {
        super(commonInfo, bookInfo, ingredient, result, experience, cookingTime);
    }

    @Override
    protected @NonNull Item furnaceIcon() { return ModBlocks.SMELTER_WORKBENCH.asItem(); }

    @Override
    public @NonNull RecipeSerializer<SmelteringRecipe> getSerializer() { return SERIALIZER; }

    @Override
    public @NonNull RecipeType<SmelteringRecipe> getType() { return ModRecipes.SMELTERING; }

    @Override
    public @NonNull RecipeBookCategory recipeBookCategory() {
        return switch (this.category()) {
            case BLOCKS -> RecipeBookCategories.FURNACE_BLOCKS;
            case FOOD -> RecipeBookCategories.FURNACE_FOOD;
            case MISC -> RecipeBookCategories.FURNACE_MISC;
        };
    }
}
