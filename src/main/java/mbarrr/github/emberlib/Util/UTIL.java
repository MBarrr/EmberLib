package mbarrr.github.emberlib.Util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UTIL {

    /**
     * Return whether a location is between two locations
     * @param firstBound First bound
     * @param secondBound Second bound
     * @param location Location to test
     * @return true if location is between the two points, otherwise false
     */
    public static boolean isLocationInBound(Location firstBound, Location secondBound, Location location){

        double x1 = firstBound.getX();
        double y1 = firstBound.getY();
        double z1 = firstBound.getZ();

        double x2 = secondBound.getX();
        double y2 = secondBound.getY();
        double z2 = secondBound.getZ();

        return (location.getX() > x1) && (location.getY() > y1) && (location.getZ() > z1) && (location.getX() < x2) && (location.getY() < y2) && (location.getZ() < z2);
    }

    /**
     * Returns the normalized inverse of a location excluding y axis
     * @param dir
     * @return The normalized inverse of the location
     */
    public static Vector getInvDir(Vector dir){
        Vector invDir = new Vector(-1/dir.getX(), 0, -1/dir.getZ());
        return invDir.normalize();
    }

    /**
     * Turn an itemstack into a string to be saved etc
     * Currently stores: material, amount, enchantments, displayname, lore
     * @param item
     * @return String or null if itemtype was air, or if item was null
     */
    public static JSONObject serializeItem(ItemStack item){
        //Get item material and amount
        if(item == null) return null;
        Material itemType = item.getType();
        int amount = item.getAmount();

        JSONObject obj = new JSONObject();
        obj.put("material", itemType);
        obj.put("amount", amount);

        ItemMeta meta = item.getItemMeta();

        if(meta == null) return obj;

        //Get item lore and display name
        JSONArray jsonLore = new JSONArray();
        List<String> lore = meta.getLore();
        String displayName = "`null`";

        if(meta.hasLore()){
            for(String str:lore){
                jsonLore.put(str);
            }
        }

        if(meta.hasDisplayName()) displayName = meta.getDisplayName();

        obj.put("lore", jsonLore);
        obj.put("displayName", displayName);

        JSONArray enchantTypes = new JSONArray();
        JSONArray enchantLevels = new JSONArray();

        if(meta.hasEnchants()){
            Map<Enchantment, Integer> enchants = meta.getEnchants();

            Set<Enchantment> keys = enchants.keySet();

            for(Enchantment key:keys){
                enchantTypes.put(key.getKey().toString());
                enchantLevels.put(enchants.get(key));
            }
        }

        obj.put("enchantTypes", enchantTypes);
        obj.put("enchantLevels", enchantLevels);

        return obj;
    }

    /**
     * Turn a serialized item into an ItemStack. Should only be used to deserialize
     * strings created by UTIL.serializeItem
     * @param serializedItem String to deserialize
     * @return Deserialized item
     */
    public static ItemStack deserializeItem(JSONObject serializedItem){

        JSONObject jsonObject = serializedItem;

        int amount = jsonObject.getInt("amount");
        String matString = jsonObject.getString("material");
        Material material = Material.valueOf(matString);

        ItemStack item = new ItemStack(material, amount);

        if(!jsonObject.has("lore") || !jsonObject.has("displayName")) return item;

        List<String> lore = new ArrayList<>();
        JSONArray jsonLore = jsonObject.getJSONArray("lore");

        for(int i = 0; i < jsonLore.length(); i++){
            lore.add(jsonLore.getString(i));
        }

        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);

        String displayName = jsonObject.getString("displayName");

        if(!displayName.equals("`null`")) meta.setDisplayName(displayName);

        JSONArray jsonEnchants = jsonObject.getJSONArray("enchantTypes");
        JSONArray jsonLevels = jsonObject.getJSONArray("enchantLevels");

        for(int i = 0; i < jsonEnchants.length(); i++){
            String ench = jsonEnchants.getString(i);
            int level = jsonLevels.getInt(i);
            Enchantment enchantment = Enchantment.getByKey(NamespacedKey.fromString(ench));
            if(enchantment == null) continue;

            meta.addEnchant(enchantment, level, true);
        }

        item.setItemMeta(meta);
        return item;
    }
}