package astreaInfinitum.client.gui.runeCarver;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants.NBT;

import org.apache.commons.lang3.tuple.MutablePair;
import org.lwjgl.opengl.GL11;

import astreaInfinitum.ModProps;
import astreaInfinitum.network.PacketHandler;
import astreaInfinitum.network.sync.MessageRuneCarverServerSync;
import astreaInfinitum.tileEntities.rune.TileEntityRuneCarver;
import astreaInfinitum.utils.NBTHelper;
import fluxedCore.util.CoordinatePair;
import fluxedCore.util.RenderUtils;
import fluxedCore.util.ResourceInformation;

public class GuiRuneCarver extends GuiContainer {
	TileEntityRuneCarver tile;

	public GuiRuneCarver(InventoryPlayer player, TileEntityRuneCarver tile) {
		super(new ContainerRuneCarver(player, tile));
		this.tile = tile;
	}

	private static final ResourceLocation texture = new ResourceLocation(ModProps.modid, "textures/gui/GUICarving.png");
	private static final ResourceLocation rune = new ResourceLocation(ModProps.modid, "textures/gui/GUICarving-Rune.png");

	private static final ResourceInformation node = new ResourceInformation(texture, new CoordinatePair(0, 245), new CoordinatePair(8, 8));
	private static final ResourceInformation nodePrev = new ResourceInformation(texture, new CoordinatePair(8, 245), new CoordinatePair(8, 8));

	int innerX, innerY;
	int innerWidth = 128, innerHeight = 128;

	private List<CoordinatePair> nodes = new ArrayList<CoordinatePair>();
	private List<MutablePair<CoordinatePair, CoordinatePair>> lines = new ArrayList<MutablePair<CoordinatePair, CoordinatePair>>();
	private CoordinatePair prevNode;

	private ItemStack prevStack;

	@Override
	public void initGui() {
		this.xSize = 222;
		this.ySize = 245;

		super.initGui();
		this.innerX = guiLeft + 48;
		this.innerY = guiTop + 10;
		this.nodes = tile.nodes;
		this.lines = tile.lines;
		this.prevNode = tile.prevNode;
	}

	@Override
	public void updateScreen() {
		if (tile.getStackInSlot(0) == null) {
			if (!nodes.isEmpty())
				nodes.clear();
			if(!lines.isEmpty()){
				lines.clear();
			}
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float p_73863_3_) {
		super.drawScreen(mouseX, mouseY, p_73863_3_);

	}

	@Override
	public void mouseClicked(int mx, int my, int button) {
		super.mouseClicked(mx, my, button);

		if (isCollidedWithNode(mx, my)) {
			if (button == 0) {
				if (prevNode != null) {
					lines.add(new MutablePair<CoordinatePair, CoordinatePair>(new CoordinatePair(prevNode.getX(), prevNode.getY()), new CoordinatePair(getNodeAt(mx, my).getX(), getNodeAt(mx, my).getY())));
					prevNode = getNodeAt(mx, my);
				}
				if (prevNode == null) {
					prevNode = getNodeAt(mx, my);
				}

			}
			PacketHandler.INSTANCE.sendToServer(new MessageRuneCarverServerSync(nodes, lines, prevNode, tile.xCoord, tile.yCoord, tile.zCoord));
		}
	}

	public CoordinatePair getNodeAt(int mx, int my) {
		if (!isCollidedWithNode(mx, my)) {
			return null;
		}
		for (CoordinatePair p : nodes) {
			int nodeX = p.getX() + innerX;
			int nodeY = p.getY() + innerY;

			if (mx > nodeX && mx < nodeX + 8) {
				if (my > nodeY && my < nodeY + 8) {
					return new CoordinatePair(nodeX - innerX + 4, nodeY - innerY + 4);
				}
			}
		}
		return null;

	}

	public boolean isCollidedWithNode(int mx, int my) {
		if (nodes.isEmpty()) {

			return false;
		} else {
			for (CoordinatePair p : nodes) {
				int nodeX = p.getX() + innerX;
				int nodeY = p.getY() + innerY;

				if (mx > nodeX && mx < nodeX + 8) {
					if (my > nodeY && my < nodeY + 8) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void drawTexturedModelRect(int x, int y, ResourceInformation info) {
		Minecraft.getMinecraft().renderEngine.bindTexture(info.getLocation());
		drawTexturedModalRect(x, y, info.getUvPair().getX(), info.getUvPair().getY(), info.getSizePair().getX(), info.getSizePair().getY());
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		if (mc != null && mc.renderEngine != null) {
			mc.renderEngine.bindTexture(texture);
		}
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		super.drawGuiContainerForegroundLayer(p_146979_1_, p_146979_2_);
		nodes.clear();
		GL11.glColor4d(1, 1, 1, 1);
		GL11.glEnable(GL11.GL_LIGHTING);
		if (tile.getStackInSlot(0) != null) {
			if (prevStack == null) {
				prevStack = tile.getStackInSlot(0).copy();
			} else if (prevStack != null && !NBTHelper.isStackEqual(prevStack, tile.getStackInSlot(0))) {
				lines.clear();
				prevStack = tile.getStackInSlot(0).copy();
			}
			if (prevNode != null) {
				drawTexturedModelRect(47 + prevNode.getX(), 10 + prevNode.getY() - 4, nodePrev);
				if (getNodeAt(prevNode.getX() - 4, prevNode.getY() - 4) != null) {
					prevNode = null;
				}
			}
			int innerX = 47;
			int innerY = 10;
			mc.renderEngine.bindTexture(rune);
			drawTexturedModelRect(innerX, innerY, new ResourceInformation(rune, new CoordinatePair(0, 0), new CoordinatePair(128, 128)));
			mc.renderEngine.bindTexture(texture);
			if (lines.isEmpty()) {
				for (int i = 0; i < NBTHelper.getTagList(tile.getStackInSlot(0), "linesLeft", NBT.TAG_COMPOUND).tagCount(); i++) {
					NBTTagCompound left = NBTHelper.getTagList(tile.getStackInSlot(0), "linesLeft", NBT.TAG_COMPOUND).getCompoundTagAt(i);
					NBTTagCompound right = NBTHelper.getTagList(tile.getStackInSlot(0), "linesRight", NBT.TAG_COMPOUND).getCompoundTagAt(i);
					lines.add(new MutablePair<CoordinatePair, CoordinatePair>(CoordinatePair.readFromNBT(left), CoordinatePair.readFromNBT(right)));
				}

			}
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			if (!lines.isEmpty()) {
				for (MutablePair<CoordinatePair, CoordinatePair> line : lines) {
					RenderUtils.drawLine(innerX + line.left.getX(), innerY + line.left.getY(), innerX + line.right.getX(), innerY + line.right.getY(), 0.8f, 0.8f, 0.8f, 7);
					RenderUtils.drawLine(innerX + line.left.getX(), innerY + line.left.getY(), innerX + line.right.getX(), innerY + line.right.getY(), 0.6f, 0.6f, 0.6f, 5);
					RenderUtils.drawLine(innerX + line.left.getX(), innerY + line.left.getY(), innerX + line.right.getX(), innerY + line.right.getY(), 0.4f, 0.4f, 0.4f, 3);
					RenderUtils.drawLine(innerX + line.left.getX(), innerY + line.left.getY(), innerX + line.right.getX(), innerY + line.right.getY(), 0.2f, 0.2f, 0.2f, 1);
				}
			}
			ItemStack rune = tile.getStackInSlot(0);
			NBTTagList nodes = NBTHelper.getTagList(rune, "nodes", NBT.TAG_COMPOUND);
			for (int i = 0; i < nodes.tagCount(); i++) {
				NBTTagCompound rnode = nodes.getCompoundTagAt(i);
				drawTexturedModelRect(innerX + rnode.getInteger("x"), innerY + rnode.getInteger("y"), node);
				this.nodes.add(new CoordinatePair(rnode.getInteger("x"), rnode.getInteger("y")));
			}
			if (prevNode != null) {
				drawTexturedModelRect(innerX + prevNode.getX() - 4, innerY + prevNode.getY() - 4, nodePrev);

			}
		} else {
			lines.clear();
			prevNode = null;
		}

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

}