package mbarrr.github.emberlib.Util;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUTIL {

    public ItemStack setItemName(ItemStack item, String name){
        return setItemName(item, Component.text(name));
    }

    public ItemStack setItemName(ItemStack item, Component name){
        ItemMeta meta = item.getItemMeta();
        meta.displayName(name);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack setItemLore(ItemStack item, List<String> lore){
        List<Component> componentLore = new ArrayList<>();

        for(String str:lore) componentLore.add(Component.text(str));

        return setItemLore(componentLore, item);
    }

    public ItemStack setItemLore(List<Component> lore, ItemStack item){
        ItemMeta meta = item.getItemMeta();
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack addItemLoreLine(ItemStack item, String loreLine){
        addItemLoreLine(item, Component.text(loreLine));
        return item;
    }

    public ItemStack addItemLoreLine(ItemStack item, Component loreLine){
        ItemMeta meta = item.getItemMeta();
        List<Component> lore = meta.lore();
        if(lore == null) lore = new ArrayList<>();
        lore.add(loreLine);
        item.setItemMeta(meta);
        return item;
    }
}
