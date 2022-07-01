struct Heap<T: Comparable> {
    var heap = [T]()

    init() {}
    init(_ h: T) { heap.append(h); heap.append(h) }

    mutating func insert(_ h: T) {
        heap.append(h)
        if heap.count == 1 { heap.append(h); return }

        func isMoveUp(_ insertIdx: Int) -> Bool {
            if insertIdx <= 1 { return false }
            return heap[insertIdx] < heap[insertIdx/2]
        }

        var insertIdx = heap.count - 1
        while isMoveUp(insertIdx) {
            let prntIdx = insertIdx / 2
            heap.swapAt(insertIdx, prntIdx)
            insertIdx = prntIdx
        }
    }

    enum moveDownStatus { case none, lf, ryt }
    mutating func pop() -> T? {
        if heap.count <= 1 { return nil }

        heap.swapAt(1, heap.count-1)
        let returnData = heap.popLast()!

        func moveDown(_ poppedIdx: Int) -> moveDownStatus {
            let lfChlIdx = poppedIdx * 2
            let rytChlIdx = lfChlIdx + 1
            if lfChlIdx >= heap.count { // 왼쪽 자손 없음
                return .none
            }
            if rytChlIdx >= heap.count { // 왼쪽 자손 있지만 오른쪽 자손 없음
                return heap[lfChlIdx] < heap[poppedIdx] ? .lf : .none
            }
            if heap[lfChlIdx] >= heap[poppedIdx] && heap[rytChlIdx] >= heap[poppedIdx] {
                return .none // 왼.오 모두 있으나 자손이 더 큼 -> 바꿀필요 없음
            }
            if heap[lfChlIdx] < heap[poppedIdx] && heap[rytChlIdx] < heap[poppedIdx] {
                return heap[lfChlIdx] < heap[rytChlIdx] ? .lf : .ryt
                // 왼.오 모두 가능하기에 더 작은것 선택
            }
            // 왼.오 중 하나가 가능함 -> 가능한것 찾아서 리턴
            return heap[lfChlIdx] < heap[poppedIdx] ? .lf : .ryt
        }

        var poppedIdx = 1
        while true {
            switch moveDown(poppedIdx) {
            case .none:
                return returnData
            case .lf:
                let lfChlIdx = poppedIdx*2
                heap.swapAt(poppedIdx, lfChlIdx)
                poppedIdx = lfChlIdx
            case .ryt:
                let rytChlIdx = poppedIdx*2 + 1
                heap.swapAt(poppedIdx, rytChlIdx)
                poppedIdx = rytChlIdx
            }
        }
    }
}

개발자 소들이의 소스코드에서 약간의 오류수정, 간결화, 최적화한 코드
