using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SetSharedMesh : MonoBehaviour {

	// Use this for initialization
	void Start () {
		Mesh mesh = GetComponent<MeshFilter>().sharedMesh;
        Mesh mesh2 = Instantiate(mesh);
        GetComponent<MeshFilter>().sharedMesh = mesh2;
	}
}
