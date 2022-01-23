function getSpace(map, height) {
    const dr = [-1, 0, 1, 0];
    const dc = [0, 1, 0, -1];

    function visit(map, v, height, r, c) {
        if(v[r][c]) return;
        v[r][c] = true;
        for(let d = 0; d < 4; d++) {
            const nr = r + dr[d];
            const nc = c + dc[d];
            if(nr >= 0 && nr < map.length && nc >= 0 && nc < map.length && map[nr][nc] > height && !v[nr][nc]) {
                visit(map, v, height, nr, nc);
            }
        }
    }
    const v = Array.from(Array(map.length), () => Array.from(Array(map.length), () => false));
    let space = 0;
    for(let r = 0; r < map.length; r++) {
        for(let c = 0; c < map.length; c++) {
            if(v[r][c] || map[r][c] <= height) continue;
            visit(map, v, height, r, c);
            space++;
        }
    }
    return space;
}
const fs = require('fs');

const [firstLine, ...inputs] = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const N = +firstLine;
const map = inputs.map(line => line.split(' ').map(n => +n));
let max = 1;

for(let height = 1; height <= 100; height++) {
    const numSpace = getSpace(map, height);
    max = Math.max(max, numSpace);    
    if(numSpace === 0) {
        break;
    }
}

console.log(max);