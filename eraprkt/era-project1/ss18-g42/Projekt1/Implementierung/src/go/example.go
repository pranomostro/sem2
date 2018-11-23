package main

import (
	"fmt"
	"math"
)

var highPrecSize = 1000
var size = 500
var lowPrec = []float64{}

var highPrec = []float64{}

func fillHighPrec() {
	current := 0.90
	increment := 0.10 / float64(highPrecSize)
	for i := 0; i <= highPrecSize; i++ {
		highPrec = append(highPrec, math.Asin(current))
		current += increment
	}
}

func fill() {
	current := 0.0
	increment := 1.0 / float64(size)
	for i := 0; i <= size; i++ {
		if current > 1.0 {
			current = 1.0
		}
		lowPrec = append(lowPrec, math.Asin(current))
		current += increment
	}
}

func main() {
	fillHighPrec()
	fill()
	number := -0.9999
	fmt.Println(arcsin(number))
	fmt.Println(math.Asin(number))
}

func arcsin(input float64) float64 {
	isNegative := input < 0
	input = math.Abs(input)

	table := lowPrec

	offset := 0.0

	if input > 0.95 {

		offset = -float64(10*highPrecSize - highPrecSize)
		input = input * float64(highPrecSize) * 10.0
		table = highPrec
	} else {
		input = input * float64(size)
	}
	input += offset
	rounded := math.Round(input)

	if rounded == input {
		fmt.Println("Just looking up!")
		result := table[int(input)]
		if isNegative {
			result = result * -1
		}
		return result
	}
	result := interpolate(math.Floor(input), math.Ceil(input), input, table)
	if isNegative {
		result = result * -1
	}
	return result
}

func interpolate(x0, x1, x float64, table []float64) float64 {
	var y0, y1 float64
	y0 = table[int(x0)]
	fmt.Println(y0)
	y1 = table[int(x1)]
	fmt.Println(y1)
	// y = y0 + ((y1-y0)/(x1-x0)) * (x - x0)
	y := y0 + ((y1-y0)/(x1-x0))*(x-x0)
	return y
}
