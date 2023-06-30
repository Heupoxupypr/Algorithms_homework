public class Main {

   static class BinaryTree{
       Node root;
       class Node{
           int value;
           Node left;
           Node right;
           boolean redcolor;
       }

       public boolean find(int value){ // O(log N)
           Node cur = root;
           while(cur != null){
               if(cur.value == value){
                   return true;
               }
               if(cur.value < value){
                   cur = cur.right;
               }else{
                   cur = cur.left;
               }
           }
           return false;
       }

       private boolean nodeAdd (Node node, int value) {
           if (node.value == value) {
               return false;
           }
           else {
               if (value < node.value){
                   if (node.left != null){
                       boolean res = nodeAdd(node.left, value);
                       node.left = rebalance (node.left);
                       return res;
                   }
                   else {
                       node.left = new Node();
                       node.left.redcolor = true;
                       node.left.value = value;
                       return true;
                   }
               }
               else {
                   if (node.right != null){
                       boolean res = nodeAdd(node.right, value);
                       node.right = rebalance (node.right);
                       return res;
                   }
                   else {
                       node.right = new Node();
                       node.right.redcolor = true;
                       node.right.value = value;
                       return true;
                   }
               }
           }
       }
       private Node rebalance (Node node){
           Node res = node;
           boolean needRebalance;
           do {
               needRebalance = false;
               if (res.right != null && res.right.redcolor == true && (res.left == null || res.left.redcolor == false)){
                   needRebalance = true;
                   res = rightSwap(res);
               }
               if (res.left != null && res.left.redcolor == true && res.left.left != null && res.left.left.redcolor == true) {
                   needRebalance = true;
                   res = leftSwap(res);
               }
               if (res.left != null && res.left.redcolor == true && res.right != null && res.right.redcolor == true) {
                   needRebalance = true;
                   colorSwap(res);
               }
           } while (needRebalance);
           return res;
       }
       private Node rightSwap (Node node){
           Node rightNode = node.right;
           Node anyNode = rightNode.left;
           rightNode.left = node;
           node.right = anyNode;
           rightNode.redcolor = node.redcolor;
           node.redcolor = true;
           return rightNode;
       }

       private Node leftSwap (Node node){
           Node leftNode = node.left;
           Node anyNode = leftNode.right;
           leftNode.right = node;
           node.left = anyNode;
           leftNode.redcolor = node.redcolor;
           node.redcolor = true;
           return leftNode;
       }
       private void colorSwap (Node node){
           node.left.redcolor = false;
           node.right.redcolor = false;
           node.redcolor = true;
       }
        public boolean add (int value){
            if (root != null){
                boolean res = nodeAdd(root, value);
                root = rebalance(root);
                root.redcolor = false;
                return res;
            }
            else {
                root = new Node();
                root.value = value;
                root.redcolor = false;
                return true;
            }
        }


        public void print(){
            print(root);
        }

        private void print(Node node){
            if(node == null)
                return;
            System.out.println(node.value);

            if(node.left != null)
                System.out.println("L:" + node.left.value);

            if(node.right != null)
                System.out.println("R:" + node.right.value);

            print(node.left);
            print(node.right);
        }

    }
    public static void main(String[] args) {
       BinaryTree redBlackTree = new BinaryTree();

        for(int i=1; i<=10; i++){
            redBlackTree.add(i);
        }

        redBlackTree.print();

    }
}