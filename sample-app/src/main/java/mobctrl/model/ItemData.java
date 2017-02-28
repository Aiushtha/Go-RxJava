package mobctrl.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author Zheng Haibo
 * @PersonalWebsite http://www.mobctrl.net
 * @Description
 */
public class ItemData implements Comparable<ItemData>,Serializable {

	public static final int ITEM_TYPE_PARENT = 0;
	public static final int ITEM_TYPE_CHILD = 1;

	public String link;
	public String explain;
	public String clazz;
	public String summary;


	private String uuid= UUID.randomUUID().toString();
	private String sha1;

	private int type;// 显示类型
	private String text;
	private String path;// 路径
	private int treeDepth = 0;// 路径的深度

	private List<ItemData> list;
	private List<ItemData> children=new ArrayList<>();

	private boolean expand;// 是否展开

	public long fileSize;//文件大小

	public boolean isExpand() {
		return expand;
	}

	public void setExpand(boolean expand) {
		this.expand = expand;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<ItemData> getChildren() {
		return children;
	}

	public void setChildren(List<ItemData> children) {
		this.children = children;
	}




	public ItemData() {

	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getTreeDepth() {
		return treeDepth;
	}

	public void setTreeDepth(int treeDepth) {
		this.treeDepth = treeDepth;
	}

	@Override
	public int compareTo(ItemData another) {
		return this.getText().compareTo(another.getText());
	}

	public long getFileSize() {
		return fileSize;
	}

	public ItemData setFileSize(long fileSize) {
		this.fileSize = fileSize;
		return this;
	}

	public String getSha1() {
		return sha1;
	}

	public ItemData setSha1(String sha1) {
		this.sha1 = sha1;
		return this;
	}



	public String getLink() {
		return link;
	}

	public ItemData setLink(String link) {
		this.link = link;
		return this;
	}

	public String getClazz() {
		return clazz;
	}

	public ItemData setClazz(String clazz) {
		this.clazz = clazz;
		return this;
	}

	public String getSummary() {
		return summary;
	}

	public ItemData setSummary(String summary) {
		this.summary = summary;
		return this;
	}

	public List<ItemData> getList() {
		return list;
	}

	public ItemData setList(List<ItemData> list) {
		this.list = list;
		return this;
	}

	@Override
	public String toString() {
		return "ItemData{" +
				"text=" + text +
				",expand=" + expand +
				", uuid='" + uuid + '\'' +
				", type=" + type +
				", treeDepth=" + treeDepth +
				'}';
	}
}