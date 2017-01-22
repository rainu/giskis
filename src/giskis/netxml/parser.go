package netxml

import (
	"io"
	"encoding/xml"
	"golang.org/x/net/html/charset"
)

func ParseNetxml(r io.Reader) (*DetectionRun, error) {
	v := DetectionRun{}

	decoder := xml.NewDecoder(r)
	decoder.CharsetReader = charset.NewReaderLabel

	err := decoder.Decode(&v)
	if err != nil {
		return nil, err
	}
	return &v, nil
}