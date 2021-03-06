package heroicarmory.init;

import heroicarmory.Reference;
import heroicarmory.items.ItemBasic;
import heroicarmory.items.Sword;
import heroicarmory.ModConfig;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.minecraft.util.ResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.KilledByPlayer;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetDamage;
import net.minecraftforge.event.LootTableLoadEvent;

import java.util.ArrayList;
import static java.lang.Math.ceil;

@Mod.EventBusSubscriber(modid=Reference.MODID)
public class ModItems {

    //ITEM VARIABLES====================================================================================================
    /*{{ITEMVARIABLES}}*/

    //MATERIAL VARIABLES================================================================================================
    /*{{MATERIALVARIABLES}}*/
	
	//creative tab
	static final CreativeTabs tabHeroicArmory = new CreativeTabs("tabHeroicArmory") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(lotrNarsil);
		}		
	};
	
	public static void init() {

	    //CREATE ITEMS==================================================================================================
		/*{{CREATEITEMS}}*/

	    //ASSIGN CREATIVE TAB===========================================================================================
		/*{{CREATIVETAB}}*/
		
		//Loot Tables
		LootTableList.register(new ResourceLocation("heroicarmory", "loot"));
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

	    //REGISTER ITEMS================================================================================================
	    /*{{REGISTERITEMS}}*/

	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {

        //REGISTER RENDERS==============================================================================================
        /*{{REGISTERRENDERS}}*/

	}
	
	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	//loot tables
	@SubscribeEvent
	public static void lootLoad(LootTableLoadEvent evt) {
		String name = evt.getName().toString();



		if (name.contains("chest")) {

            //return if not a minecraft chest
            if (ModConfig.includeModChests == false && name.contains("minecraft:") == false) {
                System.out.println("skipping loot table '" + name + "' because mod chests are disabled in config");
                return;
            }

            System.out.println("added loot to loot table '" + name + "'");

            ArrayList<LootEntryItem> entries = new ArrayList<LootEntryItem>();

            /*{{LOOTTABLEMAIN}}*/

            LootEntry[] entriesArray = entries.toArray(new LootEntry[entries.size()]);

            LootPool pool = new LootPool(entriesArray, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(1), "heroicarmorypool");

            evt.getTable().addPool(pool);

		}
	}

}