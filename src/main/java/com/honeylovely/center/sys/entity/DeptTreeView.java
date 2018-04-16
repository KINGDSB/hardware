package com.honeylovely.center.sys.entity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;

public class DeptTreeView {

	private List<TreeNode> nodes;

	public List<TreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}

	public static DeptTreeView buildResourceTree(List<JSONObject> resources, List<Long> selectedResourceIds) {
		DeptTreeView treeView = new DeptTreeView();
		treeView.setNodes(getTreeNodes(null, resources, selectedResourceIds, true));
		return treeView;
	}

	public static List<TreeNode> getTreeNodes(TreeNode node, List<JSONObject> resources,
			List<Long> selectedResourceIds, boolean recursive) {
		List<TreeNode> nodes = new ArrayList<>();

		boolean isCheck = !(selectedResourceIds == null || selectedResourceIds.size() == 0);

		for (JSONObject resource : resources) {
			if (node == null) {
				if (resource.getLong("parent_id") != 0)
					continue;

				TreeNode cTNode = new TreeNode();
				cTNode.setId(resource.getLong("id"));
				cTNode.setText(resource.getString("name"));
				if (isCheck && isChecked(selectedResourceIds, cTNode.getId())) {
					State state = new State();
					state.setChecked(true);
					cTNode.setState(state);
				}
				cTNode.setNodes(getTreeNodes(cTNode, resources, selectedResourceIds, true));
				nodes.add(cTNode);
				continue;
			}

			if (!recursive) {
				if (node.getId().equals(resource.getLong("parent_id"))) {
					TreeNode cTNode = new TreeNode();
					cTNode.setId(resource.getLong("id"));
					cTNode.setText(resource.getString("name"));

					if (isCheck && isChecked(selectedResourceIds, cTNode.getId())) {
						State state = new State();
						state.setChecked(true);
						cTNode.setState(state);
					}

					nodes.add(cTNode);
				}
			} else {
				if (node.getId().equals(resource.getLong("parent_id"))) {
					TreeNode cTNode = new TreeNode();
                    cTNode.setId(resource.getLong("id"));
                    cTNode.setText(resource.getString("name"));
					if (isCheck && isChecked(selectedResourceIds, cTNode.getId())) {
						State state = new State();
						state.setChecked(true);
						cTNode.setState(state);
					}
					cTNode.setNodes(getTreeNodes(cTNode, resources, selectedResourceIds, false));
					nodes.add(cTNode);
				}
			}
		}
		return nodes;
	}

	public static boolean isChecked(List<Long> selectedResourceIds, Long id) {
		for (Long selectedId : selectedResourceIds) {
			if (selectedId.equals(id))
				return true;
		}
		return false;
	}

	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	public static class TreeNode {
		private Long id;
		private String text;
		private String[] tags;
		private State state;
		private List<TreeNode> nodes;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String[] getTags() {
			return tags;
		}

		public void setTags(String[] tags) {
			this.tags = tags;
		}

		public State getState() {
			return state;
		}

		public void setState(State state) {
			this.state = state;
		}

		public List<TreeNode> getNodes() {
			return nodes;
		}

		public void setNodes(List<TreeNode> nodes) {
			this.nodes = nodes;
		}
	}

	public static class State {
		private boolean checked;
		private boolean disabled;
		private boolean selected;

		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}

		public boolean isDisabled() {
			return disabled;
		}

		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}
	}
}
