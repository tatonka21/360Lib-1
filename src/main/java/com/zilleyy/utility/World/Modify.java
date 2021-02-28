package com.zilleyy.utility.World;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.util.CraftMagicNumbers;

/**
 * Author: Zilleyy
 * <br>
 * Date: 26/02/2021 @ 12:24 pm AEST
 */
public class Modify {

    /**
     * Sends a block change to the client.
     * @param block The block to update.
     * @param material The material to change the block to.
     */
    public static void sendBlockChange(final org.bukkit.block.Block block, final Material material) {
        final World world = getNMSWorld(block);
        final IBlockData ibd = CraftMagicNumbers.getBlock(material).getBlockData();
        final BlockPosition bp = new BlockPosition(block.getX(), block.getY(), block.getZ());
        final IBlockData _ibd = world.getType(bp);

        world.notify(bp, _ibd, ibd, 0);
    }

    /**
     * Sets a block at a rate of 90k blocks per second.
     * <br>
     * This method achieves the same thing as
     * <br>
     * {@link org.bukkit.block.Block#setType(Material)} but is significantly faster.
     *
     * @param world The world the block is in.
     * @param x The x position of the block.
     * @param y The y position of the block.
     * @param z The x position of the block.
     * @param material The material to set the block to.
     * @param applyPhysics Whether or not physics should be applied to a physics block e.g. {@link Material#SAND}.
     */
    public static void setBlockInNativeWorld(final org.bukkit.World world, final int x, final int y, final int z, final Material material, final boolean applyPhysics) {
        final BlockPosition bp = new BlockPosition(x, y, z);
        final IBlockData ibd = CraftMagicNumbers.getBlock(material).getBlockData();
        getNMSWorld(world).setTypeAndData(bp, ibd, applyPhysics ? 3 : 2);
    }

    /**
     * Sets a block at a rate of 2.19m blocks per second.
     * <br>
     * Known Issues:
     * <ul>
     * <li>Doesn't perform lighting updates.</li>
     * <li>Doesn't send updated block to the Player.</li>
     * <li>Doesn't work if the specified coordinated isn't in a loaded chunk.</li>
     * </ul>
     *
     * @param world The world the block is in.
     * @param x The x position of the block.
     * @param y The y position of the block.
     * @param z The x position of the block.
     * @param ibd The {@link IBlockData} of the {@link Block}.
     * @param applyPhysics Whether or not physics should be applied to a physics block e.g. {@link Material#SAND}.
     *
     * @see Modify#sendBlockChange(org.bukkit.block.Block, Material)
     */
    public static void setBlockInNativeChunk(final World world, final int x, final int y, final int z, final IBlockData ibd, final boolean applyPhysics) {
        final Chunk nmsChunk = world.getChunkAt(x >> 4, z >> 4);
        final BlockPosition bp = new BlockPosition(x, y, z);
        nmsChunk.setType(bp, ibd, applyPhysics);
    }

    /**
     * Sets a block at a rate of 2.19m blocks per second.
     * <br>
     * Known Issues:
     * <ul>
     * <li>Doesn't perform lighting updates.</li>
     * <li>Doesn't send updated block to the Player.</li>
     * <li>Doesn't work if the specified coordinated isn't in a loaded chunk.</li>
     * </ul>
     *
     * @param world The world the block is in.
     * @param x The x position of the block.
     * @param y The y position of the block.
     * @param z The x position of the block.
     * @param material The material to set the block to.
     * @param applyPhysics Whether or not physics should be applied to a physics block e.g. {@link Material#SAND}.
     *
     * @see Modify#sendBlockChange(org.bukkit.block.Block, Material)
     */
    public static void setBlockInNativeChunk(final World world, final int x, final int y, final int z, final Material material, final boolean applyPhysics) {
        setBlockInNativeChunk(world, x, y, z, CraftMagicNumbers.getBlock(material).getBlockData(), applyPhysics);
    }

    /**
     * Sets a block at a rate of 7.9m blocks per second.
     * <br>
     * Known Issues:
     * <ul>
     * <li>Doesn't perform lighting updates.</li>
     * <li>Doesn't send updated block to the Player.</li>
     * <li>Doesn't work if the specified coordinated isn't in a loaded chunk.</li>
     * <li>Sometimes doesn't place the block for some reason.</li>
     * </ul>
     *
     * @param world The world the block is in.
     * @param x The x position of the block.
     * @param y The y position of the block.
     * @param z The x position of the block.
     * @param material The material to set the block to.
     *
     * @see Modify#sendBlockChange(org.bukkit.block.Block, Material)
     */
    public static void setBlockInNativeChunkSection(final org.bukkit.World world, final int x, final int y, final int z, final Material material) {
        final World nmsWorld = getNMSWorld(world);
        final Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);
        final BlockPosition bp = new BlockPosition(x, y, z);
        final IBlockData ibd = CraftMagicNumbers.getBlock(material).getBlockData();

        ChunkSection cs = nmsChunk.getSections()[y >> 4];
        if (cs == nmsChunk.a()) {
            cs = new ChunkSection(y >> 4 << 4);
            nmsChunk.getSections()[y >> 4] = cs;
        }
        cs.setType(x & 15, y & 15, z & 15, ibd);
    }

    /**
     * Sets a block at a rate of 14m blocks per second.
     * <br>
     * Known Issues:
     * <ul>
     * <li>Doesn't perform lighting updates.</li>
     * <li>Doesn't send updated block to the Player.</li>
     * <li>Doesn't work if the specified coordinated isn't in a loaded chunk.</li>
     * <li>Sometimes doesn't place the block for some reason.</li>
     * <li>The chunk must be reloaded somehow to make the block visible. e.g. Restarting the server.</li>
     * </ul>
     *
     * @param world The world the block is in.
     * @param x The x position of the block.
     * @param y The y position of the block.
     * @param z The x position of the block.
     * @param blockId The material ID to set the block to.
     * @param data The material data to apply to the block.
     * @param applyPhysics Whether or not physics should be applied to a physics block e.g. {@link Material#SAND}.
     */
    public static void setBlockInNativeDataPalette(final org.bukkit.World world, final int x, final int y, final int z, final int blockId, final byte data, final boolean applyPhysics) {
        final World nmsWorld = getNMSWorld(world);
        final Chunk nmsChunk = nmsWorld.getChunkAt(x >> 4, z >> 4);
        final IBlockData ibd = Block.getByCombinedId(blockId + (data << 12));
        final BlockPosition bp = new BlockPosition(x, y, z);
        final IBlockData _ibd = nmsChunk.getType(bp);

        ChunkSection cs = nmsChunk.getSections()[y >> 4];
        if (cs == nmsChunk.a()) {
            cs = new ChunkSection(y >> 4 << 4);
            nmsChunk.getSections()[y >> 4] = cs;
        }

        if (applyPhysics)
            cs.getBlocks().setBlock(x & 15, y & 15, z & 15, ibd);
        else
            cs.getBlocks().b(x & 15, y & 15, z & 15, ibd);

        nmsWorld.notify(bp, _ibd, ibd, 0);
    }

    public static World getNMSWorld(final org.bukkit.World world) {
        return ((CraftWorld) world).getHandle();
    }

    public static World getNMSWorld(final org.bukkit.block.Block block) {
        return getNMSWorld(block.getWorld());
    }

}
