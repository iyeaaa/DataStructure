
public class UnionFind {
    Node[] a;

    UnionFind(Node[] a) {
        this.a = a;
    }

    int find(int n) {
        return n == a[n].getParent() ? n : a[n].setParent(find(a[n].getParent()));
    }

    void union(int n, int m) {
        n = find(n);
        m = find(m);

        if (n == m) return;

        if (a[n].getRank() >= a[m].getRank())
            a[m].setParent(n);
        else
            a[n].setParent(m);
        if (a[n].getRank() == a[m].getRank())
            a[n].setRank(a[n].getRank() + 1);
    }
}
