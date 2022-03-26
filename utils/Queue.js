class Queue {
  #resetSize = 10000;
  #arr;
  #firstIndex;
  length;

  constructor() {
    this.#resetSize = 10000;
    this.#arr = [];
    this.#firstIndex = 0;
    this.length = 0;
  }

  push(item) {
    this.#arr.push(item);
    this.length++;
  }

  pop() {
    if (this.length === 0) return undefined;
    const value = this.#arr[this.#firstIndex++];
    if (this.#firstIndex === this.#resetSize) {
      this.#arr = this.#arr.slice(this.#resetSize);
      this.#firstIndex = 0;
    }
    this.length--;
    return value;
  }
}
