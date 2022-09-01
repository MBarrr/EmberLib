package mbarrr.github.emberlib.Util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

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
     * Turn an itemstack into a string to be saved etc
     * Currently stores: material, amount, enchantments, displayname, lore
     * @param item
     * @return String or null if itemtype was air, or if item was null
     */
    public static String serializeItem(ItemStack item){
        //Get item material and amount
        if(item == null) return null;
        Material itemType = item.getType();
        int amount = item.getAmount();

        JSONObject obj = new JSONObject();
        obj.put("material", itemType);
        obj.put("amount", amount);

        ItemMeta meta = item.getItemMeta();

        if(meta == null) return obj.toString();

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

        return obj.toString();
    }

    /**
     * Turn a serialized item into an ItemStack. Should only be used to deserialize
     * strings created by UTIL.serializeItem
     * @param serializedItem String to deserialize
     * @return Deserialized item
     */
    public static ItemStack deserializeItem(String serializedItem){

        JsonElement asd = JsonParser.parseString(serializedItem);
        JsonObject jsonObject = asd.getAsJsonObject();

        int amount = jsonObject.get("amount").getAsInt();
        String matString = jsonObject.get("material").getAsString();
        Material material = Material.valueOf(matString);

        ItemStack item = new ItemStack(material, amount);

        if(!jsonObject.has("lore") || !jsonObject.has("displayName")) return item;

        List<String> lore = new ArrayList<>();
        JsonArray jsonLore = jsonObject.get("lore").getAsJsonArray();

        for(int i = 0; i < jsonLore.size(); i++){
            lore.add(jsonLore.get(i).getAsString());
        }

        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);

        String displayName = jsonObject.get("displayName").getAsString();

        if(!displayName.equals("`null`")) meta.setDisplayName(displayName);

        JsonArray jsonEnchants = jsonObject.get("enchantTypes").getAsJsonArray();
        JsonArray jsonLevels = jsonObject.get("enchantLevels").getAsJsonArray();

        for(int i = 0; i < jsonEnchants.size(); i++){
            String ench = jsonEnchants.get(i).getAsString();
            int level = jsonLevels.get(i).getAsInt();
            Enchantment enchantment = Enchantment.getByKey(NamespacedKey.fromString(ench));
            if(enchantment == null) continue;

            meta.addEnchant(enchantment, level, true);
        }

        item.setItemMeta(meta);
        return item;
    }
}