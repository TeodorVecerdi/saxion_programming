package first_contact.inventory;

import first_contact.Entry;
import first_contact.misc.Utils;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;

import java.util.ArrayList;

public class InventoryScene extends Scene {

    public Inventory PlayerInventory;
    public ArrayList<MouseHotspot> inventorySlotHotspots;

    private int numSlotsStart = 4;

    public InventoryScene () {
        PlayerInventory = new Inventory();
        inventorySlotHotspots = new ArrayList<>();
        for (int i = 0; i < numSlotsStart; i++) {
            final int slot = i;
            var tri = Utils.Square2Tri(i * 150 + i * 10 + 10, 10, 150, 150);
            inventorySlotHotspots.add(new MouseHotspot().AddCollisionTriangle(tri[0]).AddCollisionTriangle(tri[1]).AddAction(() -> {
                Scene.HotspotClickedThisFrame = true;
                TrySelectSlot(slot);
            }));
        }
    }

    @Override
    public void Load () {}

    @Override
    public void update (float deltaTime) {

        //increase/decrease inventory

        int numSlots = Math.max(numSlotsStart, PlayerInventory.Items.size());
        if (numSlots > inventorySlotHotspots.size()) {
            for (int i = inventorySlotHotspots.size(); i < numSlots; i++) {
                final int slot = i;
                var tri = Utils.Square2Tri(i * 150 + i * 10 + 10, 10, 150, 150);
                inventorySlotHotspots.add(new MouseHotspot().AddCollisionTriangle(tri[0]).AddCollisionTriangle(tri[1]).AddAction(() -> {
                    Scene.HotspotClickedThisFrame = true;
                    TrySelectSlot(slot);
                }));
            }
        } else if (numSlots < inventorySlotHotspots.size() && inventorySlotHotspots.size() > numSlotsStart) {
            for (int i = inventorySlotHotspots.size() - 1; i >= numSlotsStart; i--) {
                inventorySlotHotspots.remove(i);
            }
        }

        if (PlayerInventory.Items.isEmpty() && PlayerInventory.SelectedItem != -1) PlayerInventory.SelectedItem = -1;
        for (var inventorySlotHotspot : inventorySlotHotspots) {
            inventorySlotHotspot.update(deltaTime);
        }
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.noStroke();
        int numSlots = Math.max(numSlotsStart, PlayerInventory.Items.size());
        for (int i = 0; i < numSlots; i++) {
            a.fill(0xaa222222);
            a.rect(i * 150 + i * 10 + 20, 10 + 10, 130, 130);
        }
        for (int i = 0; i < a.InventoryScene.PlayerInventory.Items.size(); i++) {
            a.image(a.InventoryScene.PlayerInventory.Items.get(i).ItemSprite(), i * 150 + i * 10 + 10, 10);
        }
        for (int i = 0; i < numSlots; i++) {
            a.fill(0xff222222);
            if (PlayerInventory.SelectedItem == i) a.fill(0xffaaaaaa);
            a.rect(i * 150 + i * 10 + 10, 10, 10, 150);
            a.rect(i * 150 + i * 10 + 150, 10, 10, 150);
            a.rect(i * 150 + i * 10 + 10, 10, 150, 10);
            a.rect(i * 150 + i * 10 + 10, 10 + 140, 150, 10);
        }

        for (var inventorySlotHotspot : inventorySlotHotspots) {
            inventorySlotHotspot.render();
        }
        a.popMatrix();
    }

    private void TrySelectSlot (int slot) {
        if (PlayerInventory.Items.size() > slot) PlayerInventory.SelectedItem = slot;
    }

}
