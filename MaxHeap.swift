
struct Heap<T: Comparable> {
    var heap = [T]()

    init() { }
    init(_ data: T) { heap.append(data); heap.append(data) }

    var isEmpty: Bool { heap.count <= 1 }

    var first: T? { isEmpty ? nil : heap[1] }

    mutating func insert(_ data: T) {
        heap.append(data)
        if isEmpty { heap.append(data)}

        var isrtIdx = heap.count - 1
        while isrtIdx >= 1 && heap[isrtIdx/2] < heap[isrtIdx] {
            heap.swapAt(isrtIdx, isrtIdx/2)
            isrtIdx = isrtIdx/2
        }
    }

    enum Direction { case none, lf, ryt }
    mutating func pop() -> T? {
        if isEmpty { return nil }

        heap.swapAt(1, heap.count-1)
        let returnData = heap.popLast()!

        func moveDown(_ pop: Int) -> Direction {
            let lf = pop * 2
            let ryt = lf + 1
            if lf >= heap.count {
                return .none
            }
            if ryt >= heap.count {
                return heap[lf] > heap[pop] ? .lf : .none
            }
            if heap[lf] <= heap[pop] && heap[ryt] <= heap[pop] {
                return .none
            }
            if heap[lf] > heap[pop] && heap[ryt] > heap[pop] {
                return heap[lf] > heap[ryt] ? .lf : .ryt
            }
            return heap[lf] > heap[pop] ? .lf : .ryt
        }

        var pop = 1
        while true {
            switch moveDown(pop) {
            case .none:
                return returnData
            case .lf:
                let chl = pop * 2
                heap.swapAt(pop, chl)
                pop = chl
            case .ryt:
                let chl = pop*2 + 1
                heap.swapAt(pop, chl)
                pop = chl
            }
        }
    }
}
