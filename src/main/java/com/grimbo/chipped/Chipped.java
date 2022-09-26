package com.grimbo.chipped;

import com.grimbo.chipped.block.*;
import com.grimbo.chipped.container.*;
import com.grimbo.chipped.item.ChippedItems;
import com.grimbo.chipped.recipe.ChippedRecipeTypes;
import com.grimbo.chipped.recipe.ChippedSerializer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.core.Direction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import net.minecraft.client.Minecraft;
import java.util.function.Predicate;

@Mod(Chipped.MOD_ID)
public class Chipped {
	public static final String MOD_ID = "chipped";
	public static final CreativeModeTab CHIPPED = (new CreativeModeTab("chippedTab") {
		@Override
		public @NotNull ItemStack makeIcon() {
			return new ItemStack(ChippedBlocks.BOTANIST_WORKBENCH.get());
		}
	}).setRecipeFolderName("chipped_tab");

	public Chipped() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ChippedBlocks.BLOCKS.register(eventBus);
		ChippedBlocks.register();
		ChippedItems.ITEMS.register(eventBus);
		ChippedRecipeTypes.RECIPE_TYPES.register(eventBus);
		ChippedSerializer.SERIALIZER.register(eventBus);
		ChippedContainerType.CONTAINER.register(eventBus);
		eventBus.addListener(this::clientRender);
		eventBus.addListener(this::onClientSetupEvent);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void checkHarvest(PlayerEvent.HarvestCheck event) {
		Item held = event.getEntity().getMainHandItem().getItem();
		if ((held instanceof SwordItem || held instanceof ShearsItem) && event.getTargetBlock().getBlock() instanceof WebBlock) {
			event.setCanHarvest(true);
		}
	}

	@SubscribeEvent
	public void clientRender(final FMLClientSetupEvent event) {

		Predicate<RenderType> transparentRenderType = renderType -> Minecraft.useFancyGraphics() ? renderType == RenderType.translucent() : renderType == RenderType.solid();
		Predicate<RenderType> cutoutRenderType = renderType -> Minecraft.useFancyGraphics() ? renderType == RenderType.cutoutMipped() : renderType == RenderType.solid();

		ItemBlockRenderTypes.setRenderLayer(ChippedBlocks.BOTANIST_WORKBENCH.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(ChippedBlocks.LOOM_TABLE.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(ChippedBlocks.ALCHEMY_BENCH.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(ChippedBlocks.MECHANIST_WORKBENCH.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(ChippedBlocks.CARPENTERS_TABLE.get(), transparentRenderType);
		ItemBlockRenderTypes.setRenderLayer(ChippedBlocks.GLASSBLOWER.get(), transparentRenderType);

		for (RegistryObject<GlassBlock> glass : ChippedBlockTypes.GLASSES) {
			ItemBlockRenderTypes.setRenderLayer(glass.get(), cutoutRenderType);
		}

		for (RegistryObject<IronBarsBlock> glassPane : ChippedBlockTypes.GLASS_PANES) {
			ItemBlockRenderTypes.setRenderLayer(glassPane.get(), cutoutRenderType);
		}

		for (int id = 0; id < 16; id++) {
			DyeColor color = DyeColor.byId(id);
			for (RegistryObject<StainedGlassBlock> stainedGlass : ChippedBlockTypes.STAINED_GLASSES.get(color)) {
				ItemBlockRenderTypes.setRenderLayer(stainedGlass.get(), transparentRenderType);
			}

			for (RegistryObject<StainedGlassPaneBlock> stainedGlassPane : ChippedBlockTypes.STAINED_GLASS_PANES.get(color)) {
				ItemBlockRenderTypes.setRenderLayer(stainedGlassPane.get(), transparentRenderType);
			}
		}

		for (RegistryObject<VineBlock> vine : ChippedBlockTypes.VINES) {
			ItemBlockRenderTypes.setRenderLayer(vine.get(), cutoutRenderType);
		}

		for (RegistryObject<MushroomBlock> brown_mushroom : ChippedBlockTypes.BROWN_MUSHROOMS) {
			ItemBlockRenderTypes.setRenderLayer(brown_mushroom.get(), cutoutRenderType);
		}

		for (RegistryObject<MushroomBlock> red_mushroom : ChippedBlockTypes.RED_MUSHROOMS) {
			ItemBlockRenderTypes.setRenderLayer(red_mushroom.get(), cutoutRenderType);
		}

		for (RegistryObject<MushroomBlock> warped_fungus : ChippedBlockTypes.WARPED_FUNGUS) {
			ItemBlockRenderTypes.setRenderLayer(warped_fungus.get(), cutoutRenderType);
		}

		for (RegistryObject<MushroomBlock> crimson_fungus : ChippedBlockTypes.CRIMSON_FUNGUS) {
			ItemBlockRenderTypes.setRenderLayer(crimson_fungus.get(), cutoutRenderType);
		}

		for (RegistryObject<RootsBlock> warped_roots : ChippedBlockTypes.WARPED_ROOTS) {
			ItemBlockRenderTypes.setRenderLayer(warped_roots.get(), cutoutRenderType);
		}

		for (RegistryObject<RootsBlock> crimson_roots : ChippedBlockTypes.CRIMSON_ROOTS) {
			ItemBlockRenderTypes.setRenderLayer(crimson_roots.get(), cutoutRenderType);
		}

		for (RegistryObject<NetherSproutsBlock> nether_sprouts : ChippedBlockTypes.NETHER_SPROUTS) {
			ItemBlockRenderTypes.setRenderLayer(nether_sprouts.get(), cutoutRenderType);
		}

		for (RegistryObject<WaterlilyBlock> lily_pad : ChippedBlockTypes.LILY_PAD) {
			ItemBlockRenderTypes.setRenderLayer(lily_pad.get(), cutoutRenderType);
		}

		for (RegistryObject<RedstoneTorchBlock> redstoneTorch : ChippedBlockTypes.REDSTONE_TORCHES) {
			ItemBlockRenderTypes.setRenderLayer(redstoneTorch.get(), cutoutRenderType);
		}

		for (RegistryObject<RedstoneWallTorchBlock> redstoneWallTorch : ChippedBlocks.REDSTONE_WALL_TORCHES) {
			ItemBlockRenderTypes.setRenderLayer(redstoneWallTorch.get(), cutoutRenderType);
		}

		for (RegistryObject<WebBlock> cobweb : ChippedBlockTypes.COBWEBS) {
			ItemBlockRenderTypes.setRenderLayer(cobweb.get(), cutoutRenderType);
		}

		for (RegistryObject<TorchBlock> torch : ChippedBlockTypes.TORCHES) {
			ItemBlockRenderTypes.setRenderLayer(torch.get(), cutoutRenderType);
		}

		for (RegistryObject<WallTorchBlock> wallTorch : ChippedBlocks.WALL_TORCHES) {
			ItemBlockRenderTypes.setRenderLayer(wallTorch.get(), cutoutRenderType);
		}

		for (RegistryObject<Block> lantern : ChippedBlockTypes.LANTERNS) {
			ItemBlockRenderTypes.setRenderLayer(lantern.get(), cutoutRenderType);
		}

		for (RegistryObject<Block> lantern : ChippedBlockTypes.SOUL_LANTERNS) {
			ItemBlockRenderTypes.setRenderLayer(lantern.get(), cutoutRenderType);
		}
	}

	@SuppressWarnings("unchecked")
	@SubscribeEvent
	public void onClientSetupEvent(FMLClientSetupEvent event) {
		for (MenuType<?> container : ChippedContainerType.CONTAINER.getEntries().stream().map(RegistryObject::get).toList()) {
			MenuScreens.register((MenuType<ChippedContainer>) container, ChippedScreen::new);
		}
	}

	public static int getTorchAngleFromDir(Direction direction) {
		return switch (direction) {
			case WEST:
				yield 180;
			case NORTH:
				yield 270;
			case SOUTH:
				yield 90;
			default:
				yield 0;
		};
	}

	public static int getAngleFromDir(Direction direction) {
		return switch (direction) {
			case WEST:
				yield 270;
			case SOUTH:
				yield 180;
			case EAST:
				yield 90;
			default:
				yield 0;
		};
	}
}
