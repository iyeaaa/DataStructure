struct UnionFind {
    var parent: [Int]

    init(cnt: Int) { parent = Array(0...cnt) }

    mutating func root(of x: Int) -> Int {
        if parent[x] == x { return x }
        parent[x] = root(of: parent[x])
        return parent[x]
    }

    mutating func merge(_ x: Int, with y: Int) {
        let (x, y) = (root(of: x), root(of: y))
        if x == y { return }
        parent[x] = y
    }

    mutating func isUnion(_ x: Int, _ y: Int) -> Bool {
        root(of: x) == root(of: y)
    }
}


