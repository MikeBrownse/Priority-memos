package data;

/**
 * �ڵ㽨��
 * 
 * @author summer
 *
 * @param <T>
 */
public class Node<T extends Database> {          //û���������ݿ�
	// �ڵ�����
	T data;
	// ���ڵ㣬�����ӽڵ�
	Node<T> fatherNode, leftChildNode, rightChildNode;
	//�Ƿ�����ڵ㡢�Ƿ����ҽڵ�
	boolean isLeftChild = false, isRightChild = false;

	//��ڵ��Ƿ����
	public boolean haveLeftChild() {
		return !(leftChildNode == null);
	}

	//�ҽڵ��Ƿ����
	public boolean haveRightChild() {
		return !(rightChildNode == null);
	}

	//���췽��
	public Node(boolean isLeft, boolean isRight) {
		isLeftChild = isLeft;
		isRightChild = isRight;
	}
	//myself-���treeRoot cannot be resolved to a variable������
		private Node<T> treeRoot;
		   /**����һ�ſյĶ��������*/
		   public void BinarySearchTree(){
			   treeRoot = null;}
	


/**
 * ����ڵ�
 * 
 * @param insertData �����������
 * @param node ��ʼ�ȽϵĽڵ�
 */
private void insertNode(T insertData, Node<T> node) {
	//Calendar insertData;
	int compareResult = (insertData.compareTo(node.data));
	if (compareResult == 0)// ���
		return;
	else if (compareResult > 0) {// ���ڽڵ�ֵ
		if (node.rightChildNode == null) {
			node.rightChildNode = new Node<T>(false, true);
			node.rightChildNode.data = insertData;// ����ֵ
			node.rightChildNode.fatherNode = node;
			return;
		} else
			insertNode(insertData, node.rightChildNode);// �����Ա����ӽڵ�
	} else {// С�ڽڵ�ֵ
		if (node.leftChildNode == null) {
			node.leftChildNode = new Node<T>(true, false);
			node.leftChildNode.data = insertData;// ����ֵ
			node.leftChildNode.fatherNode = node;
			return;
		} else
			insertNode(insertData, node.leftChildNode);// �����Ա����ӽڵ�
	}
}

/**
 * ����ڵ�-������ǿյ�
 * 
 * @param insertData �����������
 */
public void insertNode(T insertData) {
	if (treeRoot.data == null) {
		treeRoot.data = insertData;
		return;
	}
	insertNode(insertData, treeRoot);
}



/**
 * ��ĳ���ڵ㿪ʼ����
 * 
 * @param target Ŀ��ֵ
 * @param startSearchNode ��ʼ�����Ľڵ�
 * @return
 */
public Node searchNode(T target, Node startNode) {
	int compareResult = target.compareTo(startNode.data);

	if (compareResult == 0)
		return startNode;
	else if (compareResult > 0 && startNode.rightChildNode != null)
		return searchNode(target, startNode.rightChildNode);
	else if (compareResult < 0 && startNode.leftChildNode != null)
		return searchNode(target, startNode.leftChildNode);
	else
		return null;
}

/**
 * �����������ڽڵ�
 * 
 * @param target Ŀ������
 * @return null���������ڽڵ�
 */
public Node searchNode(T target) {
	if (treeRoot.data == null)
		return null;
	return searchNode(target, treeRoot);
}

/**
 * ��������
 * @param target Ŀ������(�в��ּ�����Ҫ����Ϣ����)
 * @return ����Ŀ������
 */
public  Database searchData(T target) {
	Node node = searchNode(target);
	if (node != null)
		return node.data;
	return null;
}

/**
 * ɾ���ڵ�
 * 
 * @param node ��ɾ���ڵ�
 */
private void deleteNode(Node node) {
	// �����˳�����кýڵ㣬����ǰ���ͺ�̾�����������Ͻ���������������Ľڵ�.

	// ����ڵ�ֻ����ڵ����ֻ���ҽڵ�

	if (node.haveLeftChild() && !node.haveRightChild()) {// ֻ����ڵ�
		if (node.isLeftChild) {
			node.fatherNode.leftChildNode = node.leftChildNode;

		} else if (node.isRightChild) {
			node.fatherNode.rightChildNode = node.leftChildNode;
		} else// ��ɾ���ڵ��Ǹ��ڵ�
			treeRoot = node.leftChildNode;
		node.leftChildNode.fatherNode = node.fatherNode;
	} else if (node.haveRightChild() && !node.haveLeftChild()) {// ֻ���ҽڵ�
		if (node.isLeftChild) {
			node.fatherNode.leftChildNode = node.rightChildNode;

		} else if (node.isRightChild) {
			node.fatherNode.rightChildNode = node.rightChildNode;
		} else// ��ɾ���ڵ��Ǹ��ڵ�
			treeRoot = node.rightChildNode;
		node.rightChildNode.fatherNode = node.fatherNode;
	} else if (node.haveLeftChild() && node.haveRightChild()) {// �������ӽڵ�
		Node successorNode = getSuccessorNode(node);
		if (successorNode == node.rightChildNode) {// ��̽ڵ������ӽڵ�
			successorNode.fatherNode = node.fatherNode;
			if (node.isLeftChild)
				node.fatherNode.leftChildNode = successorNode;
			else if (node.isRightChild)
				node.fatherNode.rightChildNode = successorNode;
			else {// �Ǹ��ڵ�
				successorNode = treeRoot;
			}

			successorNode.fatherNode = node.fatherNode;
			successorNode.leftChildNode = node.leftChildNode;
			node.leftChildNode.fatherNode = successorNode;

		} else {// ��̽ڵ������ӽڵ�������ӽڵ�
			if (successorNode.haveRightChild()) {// ���ӽڵ���������
				successorNode.fatherNode.leftChildNode = successorNode.rightChildNode;
				successorNode.rightChildNode.fatherNode = successorNode.fatherNode;

				replaceNode(node, successorNode);

			} else {// ���ӽڵ�û��������
					// Ҷ�ڵ㣬ֱ��ɾ��
				successorNode.fatherNode.leftChildNode = null;
				replaceNode(node, successorNode);
			}
		}

	} else {// û���ӽڵ�
		if (node.isLeftChild) {
			node.fatherNode.leftChildNode = null;
		} else if (node.isRightChild) {
			node.fatherNode.rightChildNode = null;
		}

	}

	node = null;
}

/**
 * �����ڽڵ���滻�߼�(�����ڼӴ�!)
 * @param node ���滻�ڵ�
 * @param replaceNode �滻�Ľڵ�
 */
private void replaceNode(Node node, Node replaceNode) {
	if (node.isLeftChild)
		node.fatherNode.leftChildNode = replaceNode;
	else if (node.isRightChild)
		node.fatherNode.rightChildNode = replaceNode;
	else {// node�Ǹ��ڵ�
		treeRoot = replaceNode;
	}

	node.leftChildNode.fatherNode = node.rightChildNode.fatherNode = replaceNode;
	replaceNode.leftChildNode = node.leftChildNode;
	replaceNode.rightChildNode = node.rightChildNode;
}

/**
 * ��ȡһ���ڵ�ĺ�̽ڵ�
 * @param node
 * @return
 */
private Node getSuccessorNode(Node node) {
	if (!node.haveRightChild()) {// û��������
		return null;
	}

	Node targetNode = node.rightChildNode;
	while (targetNode.haveLeftChild()) {// ���������������ӣ���֤���صĽڵ�һ��û��������
		targetNode = targetNode.leftChildNode;
	}

	return targetNode;
}
/**
 * ɾ�����е�����
 * @param  Database
 */
public void deleteData(T  Database) {
	Node node = searchNode( Database);
	deleteNode(node);
}

/**
 * �����ڵ�
 * @param node
 */
private void preOrder(Node node) {
	System.out.println("" + node.data.toString());
	if (node.haveLeftChild())
		preOrder(node.leftChildNode);

	if (node.haveRightChild())
		preOrder(node.rightChildNode);
}

/**
 * ������(ǰ�����)
 */
public void preOrder() {
	if (treeRoot == null)
		return;

	preOrder(treeRoot);

}
}
