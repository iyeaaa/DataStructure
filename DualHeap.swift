struct DualHeap<T: Comparable & Hashable> {
    struct MaxHeap<T: Comparable> {
        var heap = [T]()

        init() {}
        init(_ h: T) { heap.append(h); heap.append(h) }

        var isEmpty: Bool { heap.count <= 1 }

        var first: T? { isEmpty ? nil : heap[1] }

        mutating func insert(_ h: T) {
            heap.append(h)
            if isEmpty { heap.append(h) }
            var isrt = heap.count-1
            while isrt > 1 && heap[isrt] > heap[isrt/2] {
                heap.swapAt(isrt, isrt/2)
                isrt /= 2
            }
        }

        enum Direction { case none, lf, ryt }
        mutating func pop() -> T? {
            func haveToDown(_ pop: Int) -> Direction {
                let lf = pop*2, ryt = lf+1
                while lf >= heap.count { return .none }
                while ryt >= heap.count {
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
            if isEmpty { return nil }
            heap.swapAt(1, heap.count-1)
            let returnData = heap.popLast()!

            var pop = 1
            while true {
                switch haveToDown(pop) {
                case .none:
                    return returnData
                case .lf:
                    heap.swapAt(pop, pop*2)
                    pop *= 2
                case .ryt:
                    heap.swapAt(pop, pop*2+1)
                    pop = pop*2 + 1
                }
            }
        }
    }

    struct MinHeap<T: Comparable> {
        var heap = [T]()

        init() {}
        init(_ h: T) { heap.append(h); heap.append(h) }

        var isEmpty: Bool { heap.count <= 1 }

        var first: T? { isEmpty ? nil : heap[1] }

        mutating func insert(_ h: T) {
            heap.append(h)
            if isEmpty { heap.append(h) }
            var isrt = heap.count-1
            while isrt > 1 && heap[isrt] < heap[isrt/2] {
                heap.swapAt(isrt, isrt/2)
                isrt /= 2
            }
        }

        enum Direction { case none, lf, ryt }
        mutating func pop() -> T? {
            func haveToDown(_ pop: Int) -> Direction {
                let lf = pop*2, ryt = lf+1
                while lf >= heap.count { return .none }
                while ryt >= heap.count {
                    return heap[lf] < heap[pop] ? .lf : .none
                }
                if heap[lf] >= heap[pop] && heap[ryt] >= heap[pop] {
                    return .none
                }
                if heap[lf] < heap[pop] && heap[ryt] < heap[pop] {
                    return heap[lf] < heap[ryt] ? .lf : .ryt
                }
                return heap[lf] < heap[pop] ? .lf : .ryt
            }
            if isEmpty { return nil }
            heap.swapAt(1, heap.count-1)
            let returnData = heap.popLast()!

            var pop = 1
            while true {
                switch haveToDown(pop) {
                case .none:
                    return returnData
                case .lf:
                    heap.swapAt(pop, pop*2)
                    pop *= 2
                case .ryt:
                    heap.swapAt(pop, pop*2+1)
                    pop = pop*2 + 1
                }
            }
        }
    }

    var maxHeap = MaxHeap<T>()
    var minHeap = MinHeap<T>()
    var count = [T: Int]()

    init(){}

    mutating func maxFirst() -> T? {
        while let top = maxHeap.first {
            if count[top, default: 0] > 0 {
                break
            }
            count.removeValue(forKey: top)
            _ = maxHeap.pop()
        }
        if maxHeap.isEmpty {
            return nil
        }
        return maxHeap.first
    }

    mutating func minFirst() -> T? {
        while let top = minHeap.first {
            if count[top, default: 0] > 0 {
                break
            }
            count.removeValue(forKey: top)
            _ = minHeap.pop()
        }
        if minHeap.isEmpty {
            return nil
        }
        return minHeap.first
    }

    mutating func isEmpty() -> Bool {
        while let top = maxHeap.first {
            if count[top, default: 0] > 0 {
                break
            }
            count.removeValue(forKey: top)
            _ = maxHeap.pop()
        }
        while let top = minHeap.first {
            if count[top, default: 0] > 0 {
                break
            }
            count.removeValue(forKey: top)
            _ = minHeap.pop()
        }
        return maxHeap.isEmpty
    }

    mutating func insert(_ h: T) {
        maxHeap.insert(h)
        minHeap.insert(h)
        count[h, default: 0] += 1
    }

    mutating func maxPop() -> T? {
        while let top = maxHeap.first {
            if count[top, default: 0] > 0 {
                break
            }
            count.removeValue(forKey: top)
            _ = maxHeap.pop()
        }
        if maxHeap.isEmpty {
            return nil
        }
        count[maxHeap.first!, default: 0] -= 1
        return maxHeap.pop()
    }

    mutating func minPop() -> T? {
        while let top = minHeap.first {
            if count[top, default: 0] > 0 {
                break
            }
            count.removeValue(forKey: top)
            _ = minHeap.pop()
        }
        if minHeap.isEmpty {
            return nil
        }
        count[minHeap.first!, default: 0] -= 1
        return minHeap.pop()
    }

    mutating func remove(_ h: T) {
        count[h, default: 0] -= 1
    }
}
