package com.cisco;

import java.util.*;
import java.util.Scanner;

public class Main {
	public static List<List<GNode>> paths(GNode tree) {
		List<List<GNode>> result = new ArrayList<>();
		Stack<List<GNode>> stack = new Stack<>();

		List<GNode> rootPath = new ArrayList<>();
		rootPath.add(tree);
		stack.push(rootPath);

		while (!stack.isEmpty()) {
			List<GNode> currentPath = stack.pop();
			GNode currentNode = currentPath.get(currentPath.size() - 1);

			if (currentNode.getChildren().isEmpty()) { // We've reached a leaf!
				result.add(currentPath);
			} else {
				List<GNode> children = currentNode.getChildren();
				for (GNode child : children) {
					List<GNode> newPath = new ArrayList<>(currentPath);
					newPath.add(child);
					stack.push(newPath);
				}
			}
		}
		return result;
	}

	public static List<GNode> walkGraph(GNode tree) {
		List<GNode> result = new ArrayList<>();
		Set<GNode> visited = new HashSet<>();
		Stack<GNode> stack = new Stack<>();

		stack.push(tree);

		while(!stack.isEmpty()) {
			GNode current = stack.pop();

			if(!visited.contains(current)) {
				visited.add(current);
				result.add(current);
				List<GNode> children = current.getChildren();
				for(GNode child : children) {
					stack.push(child);
				}
			}
		}

		for(GNode g : result) {
			System.out.println(g.getName());
		}


		return result;
	}

	public static int countLeadingSpaces(String line) {
		int count = 0;
		for(char c : line.toCharArray()) {
			if(c == ' ') {
				++count;
			} else {
				break;
			}
		}

		return count;
	}


	public static void listNodes(GNode tree, int level) {
		for(GNode node : tree.getChildren()) {
			for(int i = 0; i < level; ++i) {
				System.out.print(" ");
			}
			
			System.out.println(node.getName());

			if(node.getChildren().size() > 0) {
				listNodes(node, level + 1);
			}
		}
	}


	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Input number of nodes: ");

		int nodeCount = Integer.parseInt(scanner.nextLine());

		System.out.println("Input nodes (level with leading spaces):"); 

		List<GNode> nodes = new ArrayList<>();
		Map<Integer, GNode> levelMap = new HashMap<>();

		GNode root = new XNode("");

		for(int i = 0; i < nodeCount; ++i) { // Add nodes one by one
			String inputLine 	= scanner.nextLine();
			String nodeName 	= inputLine.trim(); 
			int level 			= countLeadingSpaces(inputLine);
			GNode newNode 		= new XNode(nodeName);

			if(level == 0) {
				root = newNode;
			} else {
				GNode parent = levelMap.get(level - 1);
				parent.getChildren().add(newNode);
			}
			levelMap.put(level, newNode);
		}


		//listNodes(root, 0);
		//walkGraph(root);
		List<List<GNode>> allPaths = paths(root); // Print all paths
		for(List<GNode> path : allPaths) {
			System.out.print("( ");
			for(GNode node : path) {
				System.out.print(node.getName() + " ");
			}
			System.out.print(")");
			System.out.println();
		}
	}
}
