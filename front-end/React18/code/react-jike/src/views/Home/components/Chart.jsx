import * as echarts from 'echarts'
import { useEffect, useRef } from 'react'

const Chart = ({ title, xAxis, series, chartType = 'bar' }) => {
  const chartRef = useRef(null)
  useEffect(() => {
    var chartDom = chartRef.current
    var myChart = echarts.init(chartDom)
    var option = {
      title: {
        text: title
      },
      xAxis: {
        type: 'category',
        data: xAxis
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: series,
          type: chartType
        }
      ]
    }

    option && myChart.setOption(option)
  }, [])

  return <div ref={chartRef} style={{ width: '500px', height: '500px' }}></div>
}

export default Chart
