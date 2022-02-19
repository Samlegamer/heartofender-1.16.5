package fr.samlegamer.heartofender.structure;

import com.legacy.structure_gel.api.registry.registrar.GelStructureRegistrar;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.GelConfigJigsawStructure;
import com.legacy.structure_gel.api.structure.GelConfigStructure;

import fr.samlegamer.heartofender.config.HoeConfigsRegistry;
import fr.samlegamer.heartofender.core.HeartofEnder;
import fr.samlegamer.heartofender.structure.ship.HeartEnderShipStructure;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = HeartofEnder.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HoeStructureRegistry
{	
    public static final StructureRegistrar<NoneFeatureConfiguration, HeartEnderShipStructure> Heart_Ender_Ship = GelStructureRegistrar.of(HeartofEnder.location("heart_ender_ship"), new HeartEnderShipStructure(NoneFeatureConfiguration.CODEC, HoeConfigsRegistry.SERVER.HeartEnderShip), HeartEnderShipStructure.Piece::new, NoneFeatureConfiguration.NONE, Decoration.SURFACE_STRUCTURES);

    @SubscribeEvent
    public static void onRegistry(final RegistryEvent.Register<StructureFeature<?>> event)
    {
        IForgeRegistry<StructureFeature<?>> registry = event.getRegistry();

        Heart_Ender_Ship.handleForge(registry);
    }
    
    private static  <S extends GelConfigStructure<NoneFeatureConfiguration>> StructureRegistrar<NoneFeatureConfiguration, S> register(String locate, S structure, StructurePieceType piece){
        return GelStructureRegistrar.of(HeartofEnder.location(locate), structure, piece, NoneFeatureConfiguration.INSTANCE, Decoration.SURFACE_STRUCTURES);//.handle();
    }
    
    private static  <S extends GelConfigStructure<NoneFeatureConfiguration>> StructureRegistrar<NoneFeatureConfiguration, S> register(String locate, S structure, StructurePieceType piece, Decoration decoration){
        return GelStructureRegistrar.of(HeartofEnder.location(locate), structure, piece, NoneFeatureConfiguration.INSTANCE, decoration);
    }

    private static  <S extends GelConfigJigsawStructure> StructureRegistrar<JigsawConfiguration, S> register(String locate, S structure, StructureTemplatePool root, StructurePieceType piece, Decoration decoration){
        return GelStructureRegistrar.of(HeartofEnder.location(locate), structure, piece, new JigsawConfiguration(() -> root, 7), decoration);
    }
}