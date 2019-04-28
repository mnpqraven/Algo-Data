lev1997w@wcb.de

niegru@mathematik.uni-marburg.de


#hanoi tower
```
HANOI(n,S,Z,H) //S = Startsposition, H = Hilfsposition, Z = Zielsposition
n, pos S,Z,H
if n=1 then
	print(bewege scheibe in S auf Z)
else
	HANOI(n-1,S,H,Z)
	HANOI(1,H,Z,S)
	HANOI(n-1,H,Z,S)
```

#exponent
```
EXP
if y < 0
	y = -y
	if x != 0
		x = 1/x
	while y > 0 do
		r = r * x
		y = y - 1
return r
```
