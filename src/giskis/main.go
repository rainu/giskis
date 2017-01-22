package main

import (
	"fmt"
	"os"
	"giskis/netxml"
	"giskis/sql"
)

func main() {
	file, err := os.Open(os.Args[1])
	if err != nil {
		fmt.Printf("error: %v", err)
		return
	}
	defer file.Close()

	v, err := netxml.ParseNetxml(file)
	if err != nil {
		fmt.Printf("error: %v", err)
		return
	}

	importer := sql.NewPostgresImporter()

	fmt.Println(importer.GenerateInitScript())
	for _, insert := range importer.GenerateInserts(v) {
		fmt.Println(insert)
	}
}
