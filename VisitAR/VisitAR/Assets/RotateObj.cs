using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RotateObj : MonoBehaviour
{
	public float angle = 50;
    // Update is called once per frame
    void Update()
    {
        this.transform.Rotate(Vector3.right * Time.deltaTime * angle);
		this.transform.Rotate(Vector3.up * Time.deltaTime * angle);
    }
}
