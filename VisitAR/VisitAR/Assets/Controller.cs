using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Controller : MonoBehaviour
{

    public GameObject mapPlaceholder;

    void Start()
    {
        mapPlaceholder.SetActive(false);
    }
}
