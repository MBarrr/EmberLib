package mbarrr.github.emberlib.GUI;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public final class GUILib{

    Plugin instance;

    private final String pathStart = "guilib_";

    NamespacedKey arrowKey;

    private final ItemStack leftArrow;
    private final ItemStack rightArrow;
    private final ItemStack parentGUIArrow;


    public GUILib(Plugin instance){
        this.instance = instance;
        arrowKey = new NamespacedKey(instance, pathStart+"arrow");
        leftArrow = giveItemTags(arrowKey, 0, Material.MUSIC_DISC_CAT);
        rightArrow = giveItemTags(arrowKey, 1, Material.MUSIC_DISC_CAT);
        parentGUIArrow = giveItemTags(arrowKey, 2, Material.MUSIC_DISC_BLOCKS);
    }


    public ItemStack giveItemTags(NamespacedKey key, int tag, Material material){

        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(key, PersistentDataType.INTEGER, tag);
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public int getItemAction(ItemStack item, NamespacedKey key){
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        return(container.get(key, PersistentDataType.INTEGER));

    }

    public boolean hasItemAction(ItemStack item, NamespacedKey key){
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        return container.has(key, PersistentDataType.INTEGER);
    }

    public NamespacedKey getArrowKey(){
        return arrowKey;
    }

    public  ItemStack getLeftArrow() {
        return leftArrow;
    }

    public  ItemStack getRightArrow() {
        return rightArrow;
    }

    public  ItemStack getParentGUIArrow() {
        return parentGUIArrow;
    }
}
