struct Queue<T> {
    private var queue: [T]
    private var lf = 0, ryt = -1

    init() { queue = [T]() }
    init(_ n: [T]) { queue = n; ryt = n.count-1 }

    var isEmpty: Bool {
        ryt < lf
    }

    var first: T? {
        queue[lf]
    }

    func get(index: Int) -> T? {
        queue[lf+index]
    }

    mutating func push(_ n: T) {
        queue.append(n)
        ryt += 1
    }

    mutating func pop() -> T? {
        if isEmpty { return nil }
        defer { lf += 1 }
        return queue[lf]
    }
    
    mutating func removeFirst() {
        lf += 1
    }
}

// 스택은 구현 필요 x
// removeFirst()의 O(n)을 해결
// reversed로 구현시 생기는 removeAll()의 O(n)을 해결
// 메모리 소모량은 증가
// 문제 풀 때에 기본 자료구조에 이상한 기능 추가하는 짓은 자제하자
// 이게 제일 빠르지 않을까?
