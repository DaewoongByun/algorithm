class PriorityQueue {
  #arr;
  #maxSize;
  #comp;
  size;

  constructor(comp) {
    this.#maxSize = 10;
    this.#arr = Array.from(new Array(this.#maxSize), () => undefined);
    this.#comp = comp;
    this.size = 0;

    if (!comp) {
      this.#comp = (a, b) => a - b;
    }
  }

  #swap(i1, i2) {
    const temp = this.#arr[i1];
    this.#arr[i1] = this.#arr[i2];
    this.#arr[i2] = temp;
  }

  #expandArr() {
    this.#arr = [
      ...this.#arr,
      ...Array.from(new Array(this.#maxSize), () => undefined),
    ];
    this.#maxSize *= 2;
  }

  #getMinIndex(left, right) {
    if (this.#arr[left] === undefined && this.#arr[right] === undefined)
      return undefined;
    else if (this.#arr[right] === undefined) {
      return left;
    } else {
      if (this.#comp(this.#arr[left], this.#arr[right]) <= 0) {
        return left;
      } else {
        return right;
      }
    }
  }

  push(item) {
    if (this.size === this.#maxSize - 1) this.#expandArr();
    this.#arr[++this.size] = item;
    let index = this.size;
    while (index > 1) {
      const parentIndex = parseInt(index / 2);
      const parent = this.#arr[parentIndex];
      if (this.#comp(item, parent) < 0) {
        this.#swap(index, parentIndex);
        index = parentIndex;
      } else break;
    }
  }

  pop() {
    if (this.size === 0) return undefined;
    const value = this.#arr[1];
    this.#arr[1] = this.#arr[this.size];
    this.#arr[this.size] = undefined;
    this.size--;

    let index = 1;
    while (true) {
      const minIndex = this.#getMinIndex(index * 2, index * 2 + 1);
      if (this.#comp(this.#arr[index], this.#arr[minIndex]) > 0) {
        this.#swap(index, minIndex);
        index = minIndex;
      } else break;
    }

    return value;
  }
}
