using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RotateCanvas : MonoBehaviour
{

    private Transform thisTransform;
    void Start()
    {
        thisTransform = transform;
    }
    void Update()
    {
        thisTransform.LookAt(thisTransform.position + Camera.main.transform.rotation * Vector3.forward, Camera.main.transform.rotation * Vector3.up);
    }
}
